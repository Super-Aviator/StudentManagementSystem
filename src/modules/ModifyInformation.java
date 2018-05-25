package modules;

import JsonClass.UserInformation;
import JsonClass.Message;
import net.sf.json.JSONObject;
import test.Student;
import tools.GetConnection;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ModifyInformation extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        resp.setStatus(200);
        resp.setHeader("Access-Control-Allow-Origin", "*");

        BufferedReader sis = new BufferedReader(new InputStreamReader( req.getInputStream(),"UTF-8"));
        StringBuilder json = new StringBuilder(40);
        json.append("{");
            json .append(sis.readLine());
        json.append('}');

        Message meg = new Message();
        JSONObject jsonobject=JSONObject.fromObject(json.toString());
        UserInformation lrr=(UserInformation)JSONObject.toBean(jsonobject, UserInformation.class);
        int age=lrr.getAge();
        Long id=lrr.getId();
        String Gender=lrr.getGender();
        if(age>100||age<0||!(Gender.equals("男")||Gender.equals("女"))){
            meg.setMeg("更改失败");
            resp.getWriter().println(JSONObject.fromObject(meg));
            return;
        }

        try (Connection conn = GetConnection.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("UPDATE studentinformation SET stu_name= ?,stu_gender=?,stu_age=?,stu_birthday=?,stu_enrollment=? WHERE stu_id=?");
            ps.setString(1, lrr.getName());
            ps.setString(2, Gender);
            ps.setInt(3, age);
            ps.setDate(4, Date.valueOf(lrr.getBirthday()));
            ps.setDate(5, Date.valueOf(lrr.getEnrollemt()));
            ps.setLong(6, id);
            int i = ps.executeUpdate();
            if (i == 1) meg.setMeg("更改成功");
            else meg.setMeg("更改失败");
        } catch (SQLException e) {
            meg.setMeg("数据库错误");
            e.printStackTrace();
        }
        resp.getWriter().println(JSONObject.fromObject(meg));
    }
}
