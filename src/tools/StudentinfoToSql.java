package tools;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class StudentinfoToSql {
    public static void main(String[] args) throws IOException{
        File exc = new File("C:\\Users\\Aviator\\Desktop\\data\\student.txt");
        Scanner reader = new Scanner(exc);

        try( Connection conn=GetConnection.getConnection()){
            PreparedStatement ps=conn.prepareStatement("INSERT INTO studentinformation (stu_id,stu_name,stu_gender,stu_age,stu_birthday,stu_enrollment,class_id) VALUES (?,?,?,?,?,?,?)");
            while(reader.hasNextLine()){
                String[] data=reader.nextLine().split("\\s+");
                //System.out.println(Arrays.toString(data));
                int index=0;
                ps.setLong(1,Long.parseLong(data[index++]));
                ps.setString(2,data[index++]);
                ps.setString(3,data[index++]);
                ps.setInt(4,Integer.parseInt(data[index++]));
                ps.setDate(5,Date.valueOf(data[index++]));
                ps.setDate(6,Date.valueOf(data[index++]));
                ps.setInt(7, Integer.parseInt(data[index]));
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
