<%--
  Created by IntelliJ IDEA.
  User: Elaineee
  Date: 2020/7/25 0025
  Time: 下午 23:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Friend's favorite</title>
    <link href="../css/reset.css" rel="stylesheet" type="text/css" >
    <link href="../css/favorites3.css" rel="stylesheet" type="text/css" >
    <link href="../css/nav.css" rel="stylesheet" type="text/css" >
    <link rel="stylesheet" href="../font-awesome-4.7.0/css/font-awesome.min.css">
    <script src="../jquery-3.5.1.min.js"></script>
    <script>
        $(document).ready(function(){
            $.ajax({
                type:'POST',
                url:"<%=application.getContextPath()%>/nav",
                async: false,
                success:function (data) {
                    console.log(2);
                    console.log(data);
                    $('#myAccount').html(data);
                }
            })
        });

        $(document).ready(function(){
            $.ajax({
                type:'POST',
                url:"<%=application.getContextPath()%>/fav2?friend=<%=request.getParameter("friend")%>",
                async: false,
                success:function (data) {
                    console.log(3333);
                    console.log(data);
                    $('.image').html(data);
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



<div class="resultbox">

    <div class="result"><%=request.getParameter("friend")%>'s FAVORITES<i class="fa fa-heart fa-fw" aria-hidden="true"></i>
    </div>

    <div class="image">
    </div>

</div>

<%@include file="copyright.jsp"%>

</body>
</html>
