<%--
  Created by IntelliJ IDEA.
  User: Elaineee
  Date: 2020/7/12 0012
  Time: 下午 22:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%= session.getId()%>
    <%
        Cookie cookie = new Cookie("JSESSIONID",session.getId());
        cookie.setMaxAge(3600*24);
        response.addCookie(cookie);
    %>
</body>
</html>
