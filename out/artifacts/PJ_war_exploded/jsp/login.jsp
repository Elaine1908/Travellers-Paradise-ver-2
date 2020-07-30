<%--
  Created by IntelliJ IDEA.
  User: Elaineee
  Date: 2020/7/13 0013
  Time: 上午 0:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Log in</title>
    <link href="../css/reset.css" rel="stylesheet" type="text/css" >
    <link href="../css/login.css" rel="stylesheet" type="text/css" >
    <link rel="stylesheet" href="../font-awesome-4.7.0/css/font-awesome.min.css">
</head>
<body>

<div class="login">
    <i class="fa fa-user-circle fa-5x"></i>
    <h4>Log in for Travellers' Paradise</h4>
</div>
<div class="padding">
    <div class="input">
        <form action="../UserServlet?method=login" method="post"><!-- ../login -->
            <p>Username/Email:<br>
                <input type="text" name="username" placeholder="enter username"required><!--no pattern-->
            </p><br>
            <p>Password:<br>
                <input type="password" name="password" placeholder="enter password"required>
            </p><br>

            <p>Captcha:<br>
                <input type="text" name="CHECK_CODE_PARAM_NAME" id="captcha" required><br>
                <img alt="" width="80px" src="<%= request.getContextPath()%>/validateColorServlet">
            </p>

            <p><button name="Sign in" type="submit">Log in</button> </p><!--CSS button hover <img src="images/1.png" alt="search" width="40" height="40">-->
        </form>
    </div>
</div>
<div class="new account"><p>New to Travellers' Paradise?</p><a href="register.jsp"> Create a new account.</a></div>


<footer><span class="copyright">Copyright © 2020 Elaine1908 All Right Reserved ICP:19302010075</span></footer>
</body>
</html>