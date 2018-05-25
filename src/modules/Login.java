package modules;

import JsonClass.Message;
import exceptions.AccountException;
import net.sf.json.JSONObject;
import tools.GetConnection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class Login extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setCharacterEncoding("UTF-8");
        //设置头部
        resp.setStatus(200);
        resp.setHeader("Access-Control-Allow-Origin", "*");
        Message lr = new Message();

        //获取用户名密码和状态
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        //尝试登录
        try (Connection conn = GetConnection.getConnection()) {
            if (tools.AccountOperate.login(username, password)) {//登录成功
                lr.setMeg("登录成功");
            } else//登录失败
                lr.setMeg("密码错误");
        } catch (AccountException e) {//账号相关的错误
            lr.setMeg(e.getMessage());
            System.out.println(e.getMessage());
            //e.printStackTrace();
        } catch (SQLException e) {
            lr.setMeg("数据库错误");
            System.out.println(e.getMessage());
            //e.printStackTrace();
        }

        //写入到前端
        resp.getWriter().println(JSONObject.fromObject(lr));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("Login Page!");
        System.out.println("Login Page!");
        req.setCharacterEncoding("UTF-8");
    }
}