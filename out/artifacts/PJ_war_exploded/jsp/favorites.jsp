<%@ page import="com.Elaineee.project.DAO.impl.ImgDAOImpl" %><%--
  Created by IntelliJ IDEA.
  User: Elaineee
  Date: 2020/7/13 0013
  Time: 上午 0:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>My favorite</title>
    <link href="../css/reset.css" rel="stylesheet" type="text/css" >
    <link href="../css/favorites2.css" rel="stylesheet" type="text/css" >
    <link href="../css/nav.css" rel="stylesheet" type="text/css" >
    <link href="../css/on_off_switch.css" rel="stylesheet" type="text/css">
    <link href="../css/footprint.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="../font-awesome-4.7.0/css/font-awesome.min.css">
    <script src="../js/highlight.js"></script>
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
                url:"<%=application.getContextPath()%>/fav",
                async: false,
                success:function (data) {
                    console.log(3333);
                    console.log(data);
                    $('.image').html(data);
                }
            })
        });

        $(document).ready(function() {
            $("#onoffswitch").on('click', function(){
                clickSwitch();
            });

            var clickSwitch = function() {
                if ($("#onoffswitch").is(':checked')) {
                    console.log("在OFF的状态下");
                    $.ajax({
                        async: true,
                        type:'POST',
                        url:"<%=application.getContextPath()%>/UserServlet?method=switchoff",
                        success:function (data) {
                            console.log(666);
                        }
                    })
                } else {
                    console.log("在ON的状态下");
                    $.ajax({
                        async: true,
                        type:'POST',
                        url:"<%=application.getContextPath()%>/UserServlet?method=switchon",
                        success:function (data) {
                            console.log(888);
                        }
                    })
                }
            };
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



<div id="div1">
    <span id="span1">footprint</span>
    <div class="">
        <%
            ImgDAOImpl imgDAO = new ImgDAOImpl();
            Cookie[] cookies = request.getCookies();
            if(cookies!=null && cookies.length > 0){
                for(Cookie c:cookies){
                    String cookieName = c.getName();
                    if(cookieName.startsWith("footprint_")){
                        out.print("<div class=\"footprint\"><a href=\"details.jsp?id="+c.getValue()+"\">"+imgDAO.get(Integer.parseInt(c.getValue())).getTitle()+"</a></div>");
                    }
                }
            }
        %>
    </div>

</div>



<div class="resultbox">
    <div class="testswitch">
        <input class="testswitch-checkbox" id="onoffswitch" type="checkbox">
        <label class="testswitch-label" for="onoffswitch">
            <span class="testswitch-inner" data-on="ON" data-off="OFF"></span>
            <span class="testswitch-switch"></span>
        </label>
    </div>
    <div class="result"><%=session.getAttribute("user")%>'s FAVORITES<i class="fa fa-heart fa-fw" aria-hidden="true"></i>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        show it to your friends?

    </div>

    <div class="image">
    </div>

</div>


<script>
    window.onload=function () {
        var oDiv = document.getElementById("div1");
        oDiv.onmouseover=function () {
            move(0,10);
        }
        oDiv.onmouseout=function () {
            move(-200,-10);
        }
    }
    var timer=null;
    function  move(target,speed) {
        var oDiv = document.getElementById("div1");
        clearInterval(timer);
        timer=setInterval(function () {
            if(oDiv.offsetLeft==target)
            {
                clearInterval(timer);
            }
            else
            {
                oDiv.style.left=oDiv.offsetLeft+speed+'px';
            }
        },30)
    }
</script>
<%@include file="copyright.jsp"%>

</body>
</html>
<!--<div class="footprints">
<div class="foot">MY FOOTPRINTS </div>
<div class="images">
<a href="details.jsp"><img src="../images/browser/1.jpg" alt="image"></a>

<button onclick="alert()">DELETE</button>

</div>
</div>


<div class="searchbox">
<div class="search">FOOTPRINT<i class="fa fa-history fa-fw" aria-hidden="true"></i></div>
<div class="images">
<a href="details.jsp"><img src="../images/browser/1.jpg" alt="image"></a>

</div>

</div>


-->