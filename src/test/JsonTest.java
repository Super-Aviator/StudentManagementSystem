package test;

import exceptions.AccountException;
import JsonClass.UserInformation;
import net.sf.json.JSONArray;
import tools.GetConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JsonTest {
    public static void main(String[] args) {
        //json 测试
       /* Student s1 = new Student("熊乾坤", 6504110130l, 20);
        Student s2 = new Student("曹睿", 6504110131l, 21);
        JSONArray ja1=JSONArray.fromObject(s1);
        JSONArray ja2= JSONArray.fromObject(s2);
        System.out.println(ja1.toString());
        System.out.println(ja2.toString());
        JSONObject jo = JSONObject.fromObject(s1);
        System.out.println(jo.toString());*/

       //将字符串数组转换为JSON
       /* String[] str = { "Jack", "Tom", "90", "true" };
        JSONArray json = JSONArray.fromObject(str);
        System.out.println(json);*/

        //将接转换为JSON
        /*List<String> list = new ArrayList<>();
        list.add("Jack");
        list.add("Rose");
        JSONArray json = JSONArray.fromObject(list);
        System.out.println(json);*/


        UserInformation lr=new UserInformation();
        try(Connection conn= GetConnection.getConnection()){
            if(tools.AccountOperate.login("6504110130"    ,"123")){//登录成功
                System.out.println("登录成功");

                PreparedStatement ps=conn.prepareStatement("SELECT stu_id,stu_name,stu_gender,stu_age,stu_birthday,stu_enrollment,class_name,class_department,class_teacher FROM studentinformation,classinformation WHERE studentinformation.class_id=classinformation.class_id AND stu_id=?;");
                ps.setLong(1,Long.parseLong("6504110130"));
                ResultSet result=ps.executeQuery();
                result.next();
                int index=1;
                lr.setMeg("登录成功");
                lr.setId(result.getLong(index++));
                lr.setName(result.getString(index++));
                lr.setGender(result.getString(index++));
                lr.setAge(result.getInt(index++));
                System.out.println(result.getDate(index).getClass().getSimpleName());
                lr.setBirthday(result.getDate(index++).toString());
                lr.setEnrollemt(result.getDate(index++).toString());
                lr.setClass_name(result.getString(index++));
                lr.setDepartment(result.getString(index++));
                lr.setTeacher(result.getString(index));
            }
            else//登录失败
                lr.setMeg("密码错误");
        } catch (AccountException e) {//学号错误
            lr.setMeg(e.getMessage());
            e.printStackTrace();
        }catch (SQLException e) {//服务器错误
            lr.setMeg("服务器错误");
            e.printStackTrace();
        }
        //System.out.println(lr);
        JSONArray ja=JSONArray.fromObject(lr);
        ja.add(new Student("New",6504,12));
        System.out.println(ja);

    }
}