package modules;

import JsonClass.ScoreResult;
import net.sf.json.JSONArray;
import tools.GetConnection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetScore extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        //设置头部
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setStatus(200);
        resp.setContentType("text/json");
        //获取学号
        String username = req.getParameter("username");
        String term=req.getParameter("term");


        PrintWriter pw = resp.getWriter();
        JSONArray result = JSONArray.fromObject(new ScoreResult());

        //进行查询
        final String score1="SELECT sub_name,sub_teacher,score FROM term1,subjectinformation WHERE term1.sub_id=subjectinformation.sub_id  AND stu_id=?";
        final String score2="SELECT sub_name,sub_teacher,score FROM term2,subjectinformation WHERE term2.sub_id=subjectinformation.sub_id  AND stu_id=?";
        final String avg1="SELECT Avg(score)  FROM term1 GROUP BY stu_id HAVING stu_id=?";
        final String  avg2="SELECT  Avg(score)  FROM term2 GROUP BY stu_id HAVING stu_id=?";

        PreparedStatement ps1,ps2;

        try (Connection conn = GetConnection.getConnection()) {
            if(term!=null&&term.equals( "2")){
                ps1 = conn.prepareStatement(score2);
                ps2=conn.prepareStatement(avg2);
            }
            else{
                ps1 = conn.prepareStatement(score1);
                ps2=conn.prepareStatement(avg1);
            }


            ps1.setLong(1, Long.parseLong(username));
            ResultSet rs = ps1.executeQuery();
            while (rs.next()) {
                int index = 1;
                String sub_name = rs.getString(index++);
                String sub_teacher = rs.getString(index++);
                int score = rs.getInt(index);
                result.add(new ScoreResult(sub_name, sub_teacher, score));
            }

            //获取平均成绩
            ps2.setLong(1, Long.parseLong(username));
            rs = ps2.executeQuery();
            rs.next();
            result.add(new ScoreResult("平均成绩", null, rs.getInt(1)));

            result.remove(0);
            pw.println(result.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
