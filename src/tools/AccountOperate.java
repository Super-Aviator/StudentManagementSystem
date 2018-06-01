package tools;

import exceptions.AccountException;

import java.sql.*;

public class AccountOperate {
    /**
     * if not hava this stu_id  in database,throw Exception
     * if stu_password in database is null throw Exception
     * if  stu_passworld in database not same with this stu_password return false
     * login success return true
     */
    static Connection conn=null;

    public static void checkId(String stu_id) throws SQLException, AccountException{//检查学号是否正确
        if(conn==null)  conn=GetConnection.getConnection();
        //检测账号
        try(Statement sta = conn.createStatement()){
            ResultSet rs = sta.executeQuery("SELECT stu_id FROM studentinformation");
            boolean have=false;
            while(rs.next()){
                long id=rs.getLong(1);
                if(id==Long.parseLong(stu_id))    {
                    have=true;
                    break;
                }
            }
            if(!have)   throw new AccountException("账号错误");
        }
    }

    public static boolean login(String stu_id, String stu_password) throws SQLException, AccountException {//登录
        if (stu_id == null || stu_password == null) return false;
        //检测学号
        checkId(stu_id);
        //检测密码
        try(PreparedStatement ps = conn.prepareStatement("SELECT stu_password FROM studentinformation WHERE stu_id= ?")){
            ps.setString(1, stu_id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            if (rs.getString(1) == null) throw new AccountException("账号未注册");
            if (stu_password.equals(rs.getString(1)))//登陆成功
                return true;
            else return false;//登陆失败
        }


    }

    public static boolean register(String stu_id, String stu_password) throws SQLException, AccountException {//注册
        if (stu_id == null || stu_password == null) return false;
        //检测学号
        checkId(stu_id);

        //注册
        try(PreparedStatement ps = conn.prepareStatement("SELECT stu_password FROM studentinformation WHERE stu_id=?")){//检查账号是否已经注册过
            ps.setLong(1, Long.parseLong(stu_id));
            ResultSet rs = ps.executeQuery();
            rs.next();
            String password = rs.getString(1);
            if (password != null) throw new AccountException("账号已注册");
        }

        try(PreparedStatement ps = conn.prepareStatement("UPDATE studentinformation SET stu_password=? WHERE stu_id=?;")){//插入密码
            ps.setString(1, stu_password);
            ps.setLong(2, Long.parseLong(stu_id));
            int i = ps.executeUpdate();
            return i > 0 ? true : false;
        }
    }

    public static void main(String[] args) {
        //测试注册
        try{
            if(register("6504110130","65"))
                System.out.println("成功");
            else    System.out.println("失败");
        }catch(SQLException e){
            System.out.println("失败-");
        }catch(AccountException ra){
            System.out.println(ra);
        }

        //测试登录
        try {
            if (login("6504110130", "6"))
                System.out.println("成功");
            else System.out.println("失败");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch(AccountException e){
            System.out.println("学号错误");
        }
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        if(conn!=null)  conn.close();
    }
}
