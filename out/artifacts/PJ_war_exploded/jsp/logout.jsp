<%--
  Created by IntelliJ IDEA.
  User: Elaineee
  Date: 2020/7/24 0024
  Time: 上午 9:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Log out</title>
</head>
<body>
<%=session.getAttribute("user")
%>
<%
    session.invalidate();
    Cookie[]cookies = request.getCookies();
    for(Cookie c:cookies){
        if(c.getName().startsWith("footprint_")){

            c.setMaxAge(0);
            response.addCookie(c);
        }
    }
    request.getRequestDispatcher("/jsp/index.jsp").forward(request,response);
    //response.sendRedirect("index.jsp");
%>

</body>
</html>
