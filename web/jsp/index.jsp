<%--
  Created by IntelliJ IDEA.
  User: Elaineee
  Date: 2020/7/12 0012
  Time: 下午 22:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="false" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Travellers' Paradise</title>
    <link href="../css/reset.css" rel="stylesheet" type="text/css" >
    <link href="../css/nav.css" rel="stylesheet" type="text/css" >
    <link href="../css/Home.css" rel="stylesheet" type="text/css" >
    <link rel="stylesheet" href="../font-awesome-4.7.0/css/font-awesome.min.css">
    <script src="../js/scrollbar%20button.js"></script>
    <script src="../js/highlight.js"></script>
    <script src="../jquery-3.5.1.min.js"></script>
    <script>
        $(document).ready(function(){
        $.ajax({
            type:'POST',
            url:'<%=application.getContextPath()%>/index2',
            async: false,
            success:function (data) {
                $('#collection').html(data);
            }
        });

        $.ajax({
            type:'POST',
            url:'<%=application.getContextPath()%>/index1',
            async: false,
            success:function (data) {
                $('#photo').html(data);
            }
        });

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



<div id="lbt">
    <div id="photo">
    </div>
</div><!--高清图幻灯片展示效果-->

<div class="welcome">
    <p><a href=#label >WELCOME TO TRAVELLERS' PARADISE</a><i class="fa fa-level-down fa-fw" aria-hidden="true"></i></p>
</div>

<a name="label"></a>

<div class="collection" id="collection"></div>



<i class="fa fa-refresh fa-fw" aria-hidden="true" id="refresh"></i>
<i class="fa fa-hand-pointer-o fa-fw" aria-hidden="true" onclick="gotoTop(0.1,10)"></i>
<!--js回到顶部-->

<footer>
    <ul>
        <li>About us<br>
            Privacy
        <li>Ins<br>
            Cookie
    </ul>
    <img class="arthur" src="../travel-images/Elaine.jpg" alt="Elaine" width="50" height="50">
    <span class="copyright">Copyright © 2020 Elaine1908 All Right Reserved  ICP:19302010075</span>
</footer>
</body>
</html>