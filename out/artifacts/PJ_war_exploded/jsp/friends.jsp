<%@ page import="java.util.List" %>
<%@ page import="com.Elaineee.project.entity.TraveluserEntity" %><%--
  Created by IntelliJ IDEA.
  User: Elaineee
  Date: 2020/7/13 0013
  Time: 上午 0:43
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
    <title>My friends</title>
    <link href="<%=basePath%>/css/reset.css" rel="stylesheet" type="text/css" >
    <link href="<%=basePath%>/css/friends.css" rel="stylesheet" type="text/css" >
    <link href="<%=basePath%>/css/nav.css" rel="stylesheet" type="text/css" >
    <link rel="stylesheet" href="<%=basePath%>/font-awesome-4.7.0/css/font-awesome.min.css">
    <script src="<%=basePath%>/js/highlight.js"></script>
    <script src="<%=basePath%>/jquery-3.5.1.min.js"></script>
    <script>
        $(document).ready(function(){
            $.ajax({
                type:'POST',
                url:"<%=application.getContextPath()%>/msg",
                async: false,
                success:function (data) {
                    console.log(2);
                    console.log(data);
                    $('.request').html(data);
                }
            })
        });

        $(document).ready(function(){
            $.ajax({
                type:'POST',
                url:"<%=application.getContextPath()%>/friend?method=friend",
                async: false,
                success:function (data) {
                    $('.myfriend').html(data);
                }
            })
        });
    </script>
</head>
<body>

<div id="nav">
    <div class="div-left">
        <ul>
            <li><a href="<%=basePath%>jsp/index.jsp">Home</a></li>
            <li><a href="<%=basePath%>jsp/Search.jsp">Search</a></li>
            <li><a href="#" class="user"><%=request.getSession().getAttribute("user")%></a>
                <ul class="dropdown">
                    <li><a href="<%=basePath%>jsp/upload.jsp"><i class="fa fa-upload fa-fw" aria-hidden="true"></i>upload</a></li>
                    <li><a href="<%=basePath%>jsp/photo.jsp"><i class="fa fa-photo fa-fw" aria-hidden="true"></i>my photos</a></li>
                    <li><a href="<%=basePath%>jsp/favorites.jsp"><i class="fa fa-heart fa-fw" aria-hidden="true"></i>my favor</a></li>
                    <li><a href="<%=basePath%>jsp/friends.jsp" id="myFriend"><i class="fa fa-users fa-fw" aria-hidden="true"></i>my friends</a></li>
                    <li><a href="<%=basePath%>jsp/logout.jsp"><i class="fa fa-sign-in fa-fw" aria-hidden="true"></i>log out</a></li>
                </ul>
            </li>
        </ul><!--行内元素百搭标记-->
    </div>
</div>



<div class="left">
    <div class="search">SEARCH TO FIND<i class="fa fa-search fa-fw" aria-hidden="true"></i></div>
    <form action="<%=basePath%>/UserServlet?method=query" method="post">
        <p><input type="text" name="username"><button type="submit">GO!</button></p>
    </form>
    <div class="response">

        <%
            TraveluserEntity msg = (TraveluserEntity) request.getAttribute("query_user");
            if(msg != null){%>
            <div class="m">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%= msg.getUserName()%><button><a href="<%=basePath%>/friend?method=add&&id=<%=msg.getUid()%>">add</a></button></div><div class="m">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%= msg.getEmail()%></div>
        <%
            }else{%>
            <div class="m">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Search to find xxx.</div>
           <% }%>

        <%
            Object m = request.getAttribute("message");
            if(m != null){
                out.print("<br>");
                out.print("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");out.print("<mark>");out.print(m); out.print("</mark><br>");
            }
        %>

    </div>

    <div class="msg">MESSAGES<i class="fa fa-envelope-o fa-fw" aria-hidden="true"></i></div>
    <div class="request">

    </div>

</div>

<div class="right">
    <div class="friend">MY FRIENDS <i class="fa fa-address-book fa-fw" aria-hidden="true"></i></div>


    <div class="myfriend">

    </div>
</div>




<footer><span class="copyright">Copyright © 2020 Elaine1908 All Right Reserved ICP:19302010075</span></footer>

</body>
</html>
