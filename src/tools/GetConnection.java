package tools;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GetConnection {
    private static final String url="jdbc:mysql://localhost:3306/studentmanagementsystem?characterEncoding=utf8&useSSL=false";
    private static final String username="developer";
    private static final String userpasswd="6504110130xqk";
    private static Connection conn;

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url,username,userpasswd);
        } catch (SQLException e) {
            System.out.print("数据库连接失败:");
            String error=e.toString();
            System.out.println(error.substring(error.indexOf(':')+1));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void main(String[] args) {
        Connection c= GetConnection.getConnection();
    }
}