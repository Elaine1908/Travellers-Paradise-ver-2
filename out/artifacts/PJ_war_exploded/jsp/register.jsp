<%--
  Created by IntelliJ IDEA.
  User: Elaineee
  Date: 2020/7/13 0013
  Time: 上午 0:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    %>
    <base href="<%=basePath%>">


    <meta charset="UTF-8">
    <title>Register</title>
    <link href="<%=basePath%>/css/reset.css" rel="stylesheet" type="text/css" >
    <link href="<%=basePath%>/css/register.css" rel="stylesheet" type="text/css" >
    <link rel="stylesheet" href="<%=basePath%>/font-awesome-4.7.0/css/font-awesome.min.css">
</head>
<body>

<div class="sign up">
    <i class="fa fa-user-circle fa-5x"></i>
    <h4>Sign up for Travellers' Paradise</h4>
</div>

<div class="input">
    <%
        Object msg = request.getAttribute("message");
        if(msg != null){
            out.print("<mark>");out.print(msg); out.print("</mark><br>");
        }
    %>
    <form action="<%=basePath%>/UserServlet?method=register" method="post">
        <p>Username:<br>
            <input type="text" name="username" id="username" placeholder="create a username" value="<%= request.getParameter("username")==null?"":request.getParameter("username")%>"required><!--no pattern-->
        </p>
        <p>Email:<br>
            <input type="email" name="email" id="email" placeholder="enter email" value="<%= request.getParameter("email")==null?"":request.getParameter("email")%>"required>
        </p>
        <p>Password:<br>
            <input type="password" name="password" id="password" placeholder="your password" value="<%= request.getParameter("password")==null?"":request.getParameter("password")%>" onkeyup=pwdStrength(this.value) onblur="pwdStrength(this.value)" required>
        </p><br>

        <table width="220px" border="1" cellspacing="0" cellpadding="1" bordercolor="#eeeeee" height="22px">
                     <tr align="center" bgcolor="#f5f5f5">
                         <td width="33%" id="strength_L">weak</td>
                         <td width="33%" id="strength_M">medium</td>
                         <td width="33%" id="strength_H">strong</td>
                     </tr>
        </table>

        <p>Confirm your password:<br>
            <input type="password" name="repassword" id="repassword" placeholder="confirm password" value="<%= request.getParameter("repassword")==null?"":request.getParameter("repassword")%>"required>
        </p><br>

        <p>Captcha:<br>
            <input type="text" name="CHECK_CODE_PARAM_NAME" id="captcha" required><br>
            <img alt="" width="80px" src="<%= request.getContextPath()%>/validateColorServlet">
        </p>


        <p><button name="Sign in">Sign up </button> </p>
    </form>
</div>

<script>

    function pwdStrength(pwd) {
             O_color = "#eeeeee";
             L_color = "#ffdad0";
             M_color = "#ffc18f";
             H_color = "#a4cc8f";
             var level = 0, strength = "O";
             if (pwd == null || pwd == '') {
                     strength = "O";
                     Lcolor = Mcolor = Hcolor = O_color;
                 }
             else {
                     var mode = 0;
                     if (pwd.length <= 5)
                             mode = 0;
                     else {
                             for (i = 0; i < pwd.length; i++) {
                                     var charMode, charCode;
                                     charCode = pwd.charCodeAt(i);
                                     // 判断输入密码的类型
                                     if (charCode >= 48 && charCode <= 57) //数字
                                             charMode = 1;
                                     else if (charCode >= 65 && charCode <= 90) //大写
                                             charMode = 2;
                                     else if (charCode >= 97 && charCode <= 122) //小写
                                             charMode = 4;
                                     else
                                         charMode = 8;
                                     mode |= charMode;
                                 }
                             // 计算密码模式
                             level = 0;
                             for (i = 0; i < 4; i++) {
                                     if (mode & 1)
                                             level++;
                                     mode >>>= 1;
                                 }
                         }
                     switch (level) {
                             case 0:
                                     strength = "O";
                                     Lcolor = Mcolor = Hcolor = O_color;
                                     break;
                                 case 1:
                                     strength = "L";
                                     Lcolor = L_color;
                                     Mcolor = Hcolor = O_color;
                                     break;
                                 case 2:
                                     strength = "M";
                                     Lcolor = Mcolor = M_color;
                                     Hcolor = O_color;
                                     break;
                                 default:
                                     strength = "H";
                                     Lcolor = Mcolor = Hcolor = H_color;
                                     break;
                             }
                 }
             document.getElementById("strength_L").style.background = Lcolor;
             document.getElementById("strength_M").style.background = Mcolor;
             document.getElementById("strength_H").style.background = Hcolor;
             return strength;
         }
</script>

<footer id="footer"><span class="copyright">Copyright © 2020 Elaine1908 All Right Reserved ICP:19302010075</span></footer>
</body>
</html>