package com.itheima.web.filter;

import com.itheima.bean.User;
import com.itheima.service.UserService;
import com.itheima.utils.CookieUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        try {
            HttpServletRequest request=(HttpServletRequest)req;
            HttpServletResponse response=(HttpServletResponse)resp;
            request.setCharacterEncoding("utf-8");
            response.setContentType("text/html;charset=utf-8");
            User user=null;
            user=(User)request.getSession().getAttribute("user");
            if (user==null){
                Cookie[] cookies=request.getCookies();
                Cookie targetCookie = CookieUtils.getTargetCookie(cookies, "auto");
                if (targetCookie!=null){
                    String value = targetCookie.getValue();
                    String username = value.split("-")[0];
                    String password = value.split("-")[1];
                    UserService userService=new UserService();
                    User exituser = userService.login(username, password);
                    if(exituser != null){
                        request.getSession().setAttribute("user", exituser);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        chain.doFilter(req, resp);
       /* HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        // 如果已经是登录状态, 就不需要通过拦截器登录了
        User user =  (User) request.getSession().getAttribute("user");

        if(user == null){
            //1.  获得所有的cookie对象
            Cookie[] cookies = request.getCookies();
            //2. 获得存有用户名和密码的cookie
            Cookie targetCookie = CookieUtils.getTargetCookie(cookies, "auto");
            //3. 判断用户是否勾选了自动登录(判断cookie是否为null)
            if(targetCookie != null){//勾选了自动登录
                try {
                    //从cookie获得用户名和密码
                    String value = targetCookie.getValue();//zs-123456
                    String username =  value.split("-")[0];
                    String password =  value.split("-")[1];
                    //调用业务, 进行登录的操作 User
                    System.out.println("通过拦截器自动登录....");
                    UserService userService = new UserService();
                    User exitUser = userService.login(username, password);
                    // 如果登录成功, 把user保存到session
                    if(exitUser != null){
                        request.getSession().setAttribute("user", exitUser);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        //5.放行
        chain.doFilter(request, response);*/
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
