<%--
  Created by IntelliJ IDEA.
  User: Elaineee
  Date: 2020/7/8 0008
  Time: 下午 14:38
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>

<%-- ./login 对应于刚刚servlet的value值，用./这种相对路径是一种很好的习惯。 可避免项目迁移，部署时一些路径引用的错误。 --%>
<%-- java代码放在<% %> 里面 --%>


<form action="./register" method="post">
  First name: <input type="text" name="firstname" placeholder="enter firstname"> <br>
  Last name: <input type="text" name="lastname" placeholder="enter lastname"> <br>
  Email: <input type="text" name="email" placeholder="enter Email"> <br>
  <button type="submit">Submit</button>
</form>

</body>
</html>
