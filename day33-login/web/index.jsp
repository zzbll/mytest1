<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/10/10
  Time: 11:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>

  <form action="${pageContext.request.contextPath}/user" method="post">
    <input type="hidden" name="method" value="login">
    账号<input type="text" name="username"><br/>
    密码<input type="text" name="password"><br/>
    <input type="checkbox" value="自动登录" name="auto">自动登录<br/>
    <input type="submit" value="登录">
    <c:if test="${not empty user}">
      <%--这里找的是session里的存储值--%>
      登录成功
    </c:if>
  </form>
  </body>
</html>
