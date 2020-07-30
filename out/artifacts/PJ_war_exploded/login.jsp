<%--
  Created by IntelliJ IDEA.
  User: Elaineee
  Date: 2020/7/8 0008
  Time: 下午 14:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.Elaineee.project.entity.dog" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>$Title$</title>
</head>
<body>

<%-- ./login 对应于刚刚servlet的value值，用./这种相对路径是一种很好的习惯。 可避免项目迁移，部署时一些路径引用的错误。 --%>
<%-- java代码放在<% %> 里面 --%>

<%
  dog dog = new dog();
  System.out.println(dog.getName());
%>

<form action="./login" method="post"> Username: <input type="text" name="username" placeholder="enter username"> <br>
  Password: <input type="password" name="password" placeholder="enter password"> <br>
  <button type="submit">Submit</button>
</form>

<p align="center"><a href="register.jsp" color=blue>register</a></p>

</body>
</html>
