package test;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MySecondServlet extends HttpServlet{
    protected void doGet(HttpServletRequest request , HttpServletResponse response) throws IOException{
        response.getWriter().println("This is my second servlet!");
    }
}