package modules;


import JsonClass.Message;
import exceptions.AccountException;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class Register extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest  req, HttpServletResponse resp) throws IOException, ServletException {
        resp.setCharacterEncoding("UTF-8");
        //设置头部
        resp.setStatus(200);
        resp.setHeader("Access-Control-Allow-Origin","*");
        Message meg=new Message();
        //获取用户名密码和密码
        String username=req.getParameter("username");
        String password=req.getParameter("password");


        //执行注册
        try{
            if(tools.AccountOperate.register(username,password))
                meg.setMeg("注册成功");
            else
                meg.setMeg("注册失败");
        } catch (AccountException e) {//账号相关的错误
            meg.setMeg(e.getMessage());
            e.printStackTrace();
        } catch (SQLException e) {
            meg.setMeg("数据库错误");
            e.printStackTrace();
        }

        //写入到前端
        resp.getWriter().println(JSONObject.fromObject(meg));
    }

    protected  void doGet(HttpServletRequest req,HttpServletResponse resp) throws IOException{
        resp.setCharacterEncoding("UTF-8");
        resp.setHeader("Access-Control-Allow-Origin","*");
        resp.setStatus(200);
    }
}
