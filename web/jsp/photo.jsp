<%--
  Created by IntelliJ IDEA.
  User: Elaineee
  Date: 2020/7/13 0013
  Time: 上午 0:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>My Photos</title>
    <link href="../css/reset.css" rel="stylesheet" type="text/css" >
    <link href="../css/photo.css" rel="stylesheet" type="text/css" >
    <link href="../css/nav.css" rel="stylesheet" type="text/css" >
    <link rel="stylesheet" href="../font-awesome-4.7.0/css/font-awesome.min.css">
    <script src="../js/highlight.js"></script>
    <script src="../jquery-3.5.1.min.js"></script>
    <script>
        $.ajax({
            async: true,
            type:'POST',
            url:'<%=application.getContextPath()%>/photo',
            success:function (data) {
                $('#photo').html(data);
            }
        });

        $(document).ready(function(){
            $.ajax({
                type:'POST',
                url:"<%=application.getContextPath()%>/nav",
                async: false,
                success:function (data) {
                    $('#myAccount').html(data);
                }
            })
        });

    </script>
</head>
<body>


<div id="nav">
    <div class="div-left">
        <ul>
            <li><a href="index.jsp" id="home">Home</a></li>
            <li><a href="Search.jsp" id="search">Search</a></li>
            <li id="myAccount"></li>
        </ul><!--行内元素百搭标记-->
    </div>
</div>



<div class="photo">MY PHOTOS <i class="fa fa-photo fa-fw" aria-hidden="true"></i></div>


<div id="photo"></div>


<footer><span class="copyright">Copyright © 2020 Elaine1908 All Right Reserved ICP:19302010075</span></footer>
</body>
</html>
