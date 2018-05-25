package modules;

import JsonClass.UserInformation;
import net.sf.json.JSONObject;
import tools.GetConnection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GetStudentInformation extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        String username = req.getParameter("username");

        //设置头文件
        resp.setStatus(200);
        resp.setHeader("Access-Control-Allow-Origin", "*");

        UserInformation lr = new UserInformation();

        try (Connection conn = GetConnection.getConnection()) {
            //获取该学生的信息。
            PreparedStatement ps = conn.prepareStatement("SELECT stu_id,stu_name,stu_gender,stu_age,stu_birthday,stu_enrollment,class_name,class_department,class_teacher FROM studentinformation,classinformation WHERE studentinformation.class_id=classinformation.class_id AND stu_id=?;");
            ps.setLong(1, Long.parseLong(username));
            ResultSet result = ps.executeQuery();
            result.next();
            int index = 1;
            lr.setMeg("登录成功");
            lr.setId(result.getLong(index++));
            lr.setName(result.getString(index++));
            lr.setGender(result.getString(index++));
            lr.setAge(result.getInt(index++));
            lr.setBirthday(result.getDate(index++).toString());//时间要特别注意直接将Date转换为字符串比较好，因为前端获得Date对象没有用处。
            lr.setEnrollemt(result.getDate(index++).toString());
            lr.setClass_name(result.getString(index++));
            lr.setDepartment(result.getString(index++));
            lr.setTeacher(result.getString(index));

            //获取服务器中上次登录时间
            String lastLoginTime = (String) getServletContext().getAttribute(username);
            SimpleDateFormat sim = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            if (lastLoginTime != null) {
                String date = sim.format(new Date()).toString();
                getServletContext().setAttribute(username, date);
                lr.setLastLogTime("上次登录时间为：" + lastLoginTime);
                getServletContext().setAttribute("username",date);
            } else {
                lr.setLastLogTime("您是第一次登录哦,祝您使用愉快。YoY");
                getServletContext().setAttribute(username, sim.format(new Date()).toString());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        //写入到前端
        resp.getWriter().println(JSONObject.fromObject(lr));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doGet");
        System.out.println("doGet");
    }
}
