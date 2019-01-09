package com.itheima.web;

import com.itheima.bean.User;
import com.itheima.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

@WebServlet("/user")
public class LoginServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setCharacterEncoding("utf-8");
            response.setContentType("text/html;charset=utf-8");
            String method=request.getParameter("method");
            System.out.println(method);
            Class aClass = this.getClass();
            Method method1 = aClass.getMethod(method, HttpServletRequest.class, HttpServletResponse.class);
            method1.invoke(this,request,response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            System.out.println(username+password);
            UserService userService=new UserService();
            User user=userService.login(username,password);
            String auto=request.getParameter("auto");
            System.out.println(auto);
            if(user!=null) {
                request.getSession().setAttribute("user",user);
                if (auto!=null){
                    Cookie cookie=new Cookie("auto",username+"-"+password);
                    cookie.setMaxAge(60*60*24*7);
                    cookie.setPath(request.getContextPath());
                    request.getSession().setAttribute("auto1","auto");
                    response.addCookie(cookie);
                    response.sendRedirect(request.getContextPath()+"/index.jsp");
                }else{
                    Cookie cookie=new Cookie("auto","");
                    cookie.setMaxAge(0);
                    cookie.setPath(request.getContextPath());
                    response.addCookie(cookie);
                    response.getWriter().print("登录成功");
                }
//                response.getWriter().print("登录成功");


            }else{
                response.getWriter().print("失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().print("登录失败");
        }
    }


}
