package test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Enumeration;

public class MyFirstServlet extends HttpServlet {
    PrintWriter out;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(200);
        resp.setContentType("text/json");
        resp.setContentType("0");

        resp.setHeader("Access-Control-Allow-Origin","*");//解决js不能跨域的问题

        resp.getWriter().println("HelloWorld!");
        System.out.println("Done!");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doGet");
        resp.setStatus(200);//设置状态码
        resp.setContentType("text/json;charset=UTF-8");//设置内容格式与编码
        PrintWriter out = resp.getWriter();//获取字符流进行写操作

        resp.setHeader("Access-Control-Allow-Origin","*");//解决跨域问题
        /*sb.println("this is my first servlet!");//包含转发-请求转发
        RequestDispatcher rd = req.getRequestDispatcher("/MySecondServlet");
        rd.include(req, resp);*/

        System.out.println(req.getQueryString());//获取参数字段

        /*ServletConfig sc = this.getServletConfig();//获取局部变量
        System.out.println(sc.getInitParameter("username"));
        System.out.println(sc.getInitParameter("password"));*/

        /*ServletContext sct=this.getServletContext();//获取全局变量
        System.out.println(sct.getInitParameter("overall"));*/

        /*//测试HttpServletRequest
        System.out.println(req.getLocalAddr());
        System.out.println(req.getLocalPort());
        System.out.println(req.getParameter("username"));
        Enumeration e =req.getParameterNames();//得到url中的参数
        while(e.hasMoreElements()){
            String[] u=req.getParameterValues((String)e.nextElement());
            System.out.println(Arrays.toString(u));
        }*/


        /*//给response设置一个cookie
        //!Cookie c=new Cookie("usrname","熊乾坤");//如果值为中文，则会发不e出去。
        Cookie c=new Cookie("username", URLEncoder.encode("熊乾坤","UTF-8"));//对中文进行编码URLEncoder
        c.setMaxAge(10000);
        resp.addCookie(c);
        System.out.println("I had send!");
        resp.getWriter().println("我发送了一个Cookie");*/


        /*//获取Cookie并删除Cookies
        Cookie [] c=req.getCookies();
        for(int i=0;i<c.length;i++){
            String name=c[i].getName();
            String value= URLDecoder.decode(c[i].getValue(), "UTF-8");//对中文进行解码URLDecoder
            System.out.println(name+":"+value);
            if(name.equals("username")){
                c[i].setMaxAge(0);//删除该Cookie名为username的Cookie
                resp.addCookie(c[i]);//别忘了添加到response中
                out.println("我删除了一个Cookie");
            }
        }*/

        /*//利用cookies获取上次登录的时间
        SimpleDateFormat adf=new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");//格式化时间,中文需要编码。
        Cookie[] cookies=req.getCookies();
        String value=null;
        for(int i=0;i<cookies.length;i++){
            if(cookies[i].getName().equals("login_date")){
                value=cookies[i].getValue();
                out.println(String.format("您上次登录的时间是：" + URLDecoder.decode(value,"UTF-8")));
                cookies[i].setValue(URLEncoder.encode(adf.format(new Date()),"UTF-8"));
                resp.addCookie(cookies[i]);
                break;
            }
        }

        if(value==null){
            out.println("您是第一次登录哦,祝您使用愉快。YoY");
            Cookie k=new Cookie("login_date",URLEncoder.encode(adf.format(new Date()),"UTF-8"));
            k.setMaxAge(20000);
            resp.addCookie(k);
        }*/
        System.out.println(req.getRequestURI());
    }

    @Override
    public void init(){
        System.out.println("我被创建啦！");
    }//测试生命周期

    @Override
    public void destroy(){
        System.out.println("我被销毁了！");
    }//测试生命周期
}