<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.Elaineee.project.DAO.impl.ImgDAOImpl" %><%--
  Created by IntelliJ IDEA.
  User: Elaineee
  Date: 2020/7/13 0013
  Time: 上午 0:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Details</title>
    <link href="../css/reset.css" rel="stylesheet" type="text/css" >
    <link href="../css/details.css" rel="stylesheet" type="text/css" >
    <link href="../css/nav.css" rel="stylesheet" type="text/css" >
    <link href="../css/zoom.css" rel="stylesheet" type="text/css" >
    <link rel="stylesheet" href="../font-awesome-4.7.0/css/font-awesome.min.css">
    <script src="../jquery-3.5.1.min.js"></script>
    <script>
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

        $(document).ready(function(){
            $.ajax({
                type:'POST',
                url:"<%=application.getContextPath()%>/detail?id=<%=request.getParameter("id")%>",
                async: false,
                success:function (data) {
                    $('.content').html(data);
                }
            })
        });

    </script>
</head>
<body>
<%
    if (session.getAttribute("user") != null) {
        ImgDAOImpl imgDAO = new ImgDAOImpl();
        int ImageID = Integer.parseInt(request.getParameter("id"));
        Cookie[] cookies = request.getCookies();
        List<Cookie> footprints = new ArrayList<Cookie>();
        Cookie tempcookie = null;

        if(cookies!=null && cookies.length >0){//将现有的cookie加入arraylist
            for(Cookie c:cookies){
                String cookieName = c.getName();
                if(cookieName.startsWith("footprint_")){
                    footprints.add(c);

                    if(c.getValue().equals(request.getParameter("id"))){
                        tempcookie = c;
                    }
                }
            }
        }

        if(footprints.size()>=10 && tempcookie==null){
            tempcookie=footprints.get(0);
        }
        if(tempcookie!=null){
            tempcookie.setMaxAge(0);
            response.addCookie(tempcookie);
        }

        Cookie cookie = new Cookie("footprint_"+request.getParameter("id"),request.getParameter("id"));
        response.addCookie(cookie);
    }

%>

<div id="nav">
    <div class="div-left">
        <ul>
            <li><a href="index.jsp" id="home">Home</a></li>
            <li><a href="Search.jsp" id="search">Search</a></li>
            <li id="myAccount"></li>
        </ul><!--行内元素百搭标记-->
    </div>
</div>

<div class="details">DETAILS OF THE PIC <i class="fa fa-image fa-fw" aria-hidden="true"></i></div>

<div class="content">

</div>

<script src="../js/tools.js"></script>
<script>
    window.onload = function () {
        //需求：鼠标放到小盒子上，让大盒子里面的图片和我们同步等比例移动。
        //技术点：οnmοuseenter==onmouseover 第一个不冒泡
        //技术点：οnmοuseleave==onmouseout  第一个不冒泡
        //步骤：
        //1.鼠标放上去显示盒子，移开隐藏盒子。
        //2.老三步和新五步（黄盒子跟随移动）
        //3.右侧的大图片，等比例移动。

        //0.获取相关元素
        var box = document.getElementsByClassName("box")[0];
        var small = box.firstElementChild || box.firstChild;
        var big = box.children[1];
        var mask = small.children[1];
        var bigImg = big.children[0];

        //1.鼠标放上去显示盒子，移开隐藏盒子。(为小盒子绑定事件)
        small.onmouseenter = function () {
            //封装好方法调用：显示元素
            show(mask);
            show(big);
        }
        small.onmouseleave = function () {
            //封装好方法调用：隐藏元素
            hide(mask);
            hide(big);
        }

        //2.老三步和新五步（黄盒子跟随移动）
        //绑定的事件是onmousemove，而事件源是small(只要在小盒子上移动1像素，黄盒子也要跟随)
        small.onmousemove = function (event) {
            //新五步
            event = event || window.event;

            //想要移动黄盒子，必须要知道鼠标在small小图中的位置。
            var pagex = event.pageX || scroll().left + event.clientX;
            var pagey = event.pageY || scroll().top + event.clientY;

            //x：mask的left值，y：mask的top值。
            var x = pagex - box.offsetLeft - mask.offsetWidth / 2; //除以2，可以保证鼠标mask的中间
            var y = pagey - box.offsetTop - mask.offsetHeight / 2;

            //限制换盒子的范围
            //left取值为大于0，小盒子的宽-mask的宽。
            if (x < 0) {
                x = 0;
            }
            if (x > small.offsetWidth - mask.offsetWidth) {
                x = small.offsetWidth - mask.offsetWidth;
            }
            //top同理。
            if (y < 0) {
                y = 0;
            }
            if (y > small.offsetHeight - mask.offsetHeight) {
                y = small.offsetHeight - mask.offsetHeight;
            }

            //移动黄盒子
            console.log(small.offsetHeight);
            mask.style.left = x + "px";
            mask.style.top = y + "px";

            //3.右侧的大图片，等比例移动。
            //如何移动大图片？等比例移动。
            //    大图片/大盒子 = 小图片/mask盒子
            //    大图片走的距离/mask走的距离 = （大图片-大盒子）/（小图片-黄盒子）
//                var bili = (bigImg.offsetWidth-big.offsetWidth)/(small.offsetWidth-mask.offsetWidth);

            //大图片走的距离/mask盒子都的距离 = 大图片/小图片
            var bili = bigImg.offsetWidth / small.offsetWidth;

            var xx = bili * x;  //知道比例，就可以移动大图片了
            var yy = bili * y;

            bigImg.style.marginTop = -yy + "px";
            bigImg.style.marginLeft = -xx + "px";
        }
    }
</script>

<footer><span class="copyright">Copyright © 2020 Elaine1908 All Right Reserved ICP:19302010075</span></footer>
</body>
</html>
