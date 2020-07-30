<%@ page import="java.util.List" %>
<%@ page import="com.Elaineee.project.entity.TravelimageEntity" %>
<%@ page import="com.Elaineee.project.DAO.impl.ImgDAOImpl" %><%--
  Created by IntelliJ IDEA.
  User: Elaineee
  Date: 2020/7/13 0013
  Time: 上午 0:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    %>

    <meta charset="UTF-8">
    <title>Search</title>
    <link href="../css/reset.css" rel="stylesheet" type="text/css" >
    <link href="../css/Search.css" rel="stylesheet" type="text/css" >
    <link href="../css/nav.css" rel="stylesheet" type="text/css" >
    <link rel="stylesheet" href="../font-awesome-4.7.0/css/font-awesome.min.css">
     <script src="../js/highlight.js"></script>
    <script src="../jquery-3.5.1.min.js"></script>
    <script>
        /**$(document).ready(function(){
        $.ajax({
            type:'POST',
            url:"<%=application.getContextPath()%>/nav",
            async: false,
            success:function (data) {
                $('#myAccount').html(data);
            }
        }); });
            /**$.ajax({
                type:'POST',
                url:"/initial",
                async: false,
                success:function (data) {
                    $('#r').html(data);
                }
            })
       */

        $('.filter').onclick(function () {
                document.getElementById("r").innerHTML='';
        });
    </script>
</head>
<body>


<div id="nav">
    <div class="div-left">
        <ul>
            <li><a href="<%=basePath%>jsp/index.jsp" id="home">Home</a></li>
            <li><a href="<%=basePath%>jsp/Search.jsp" id="search">Search</a></li>
            <li id="myAccount">
                <%
                    if(session.getAttribute("user")==null){
                        out.write("<li><a href=\""+basePath+"jsp/login.jsp\" class=\"user\">Log in </a></li>");
                    }
                    else {
                        out.write("<a href=\"#\" class=\"user\">"+ session.getAttribute("user") + "</a>\n" +
                                "            <ul class=\"dropdown\">\n" +
                                "                <li><a href=\""+basePath+"jsp/upload.jsp\" id=\"upload\"><i class=\"fa fa-upload fa-fw\" aria-hidden=\"true\"></i>upload</a></li>\n" +
                                "                <li><a href=\""+basePath+"jsp/photo.jsp\" id=\"myPhoto\"><i class=\"fa fa-photo fa-fw\" aria-hidden=\"true\"></i>my photos</a></li>\n" +
                                "                <li><a href=\""+basePath+"jsp/favorites.jsp\" id=\"myFavor\"><i class=\"fa fa-heart fa-fw\" aria-hidden=\"true\"></i>my favor</a></li>\n" +
                                "                <li><a href=\""+basePath+"jsp/friends.jsp\" id=\"myFavor\"><i class=\"fa fa-users fa-fw\" aria-hidden=\"true\"></i>my friends</a></li>\n" +
                                "                <li><a href=\""+basePath+"jsp/logout.jsp\"><i class=\"fa fa-sign-out fa-fw\" aria-hidden=\"true\"></i>log out</a></li>\n" +
                                "            </ul>\n" +
                                "       \n");
                    }
                %>
            </li>
        </ul><!--行内元素百搭标记-->
    </div>
</div>




<div class="searchbox">
    <div class="search">SEARCH</div>

    <div class="input">
        <form action="Search.jsp" method="post" id="filter">
            <input type="search" placeholder="search..." name="search" class="input" required><br>
            <select name="filter" id="content">
                <option value="0">-----Filter by-----</option>
                <option value="title">title</option>
                <option value="content">content</option>
            </select><br>
            <select name="rank" id="select">
                <option value="0">-----Rank by-----</option>
                <option value="heat">popularity</option>
                <option value="time">time</option>
            </select><br>
        <button class="filter" type="submit"> Filter</button>
        </form>
    </div>

</div>


<div class="resultbox">
    <div class="result">RESULT</div>
    <div id="r">
    <%
        //List<TravelimageEntity> list = (List<TravelimageEntity>)request.getAttribute("result");

        ImgDAOImpl imgDAO = new ImgDAOImpl();
        String input = request.getParameter("search")==null?"":request.getParameter("search");
        String filter = request.getParameter("filter")==null?"":request.getParameter("filter");
        String rank = request.getParameter("rank")==null?"":request.getParameter("rank");
        List<TravelimageEntity> list = imgDAO.title_heat(input);

        if((filter.equals("0") || rank.equals("0"))){
            out.print("<script>alert(\"You haven't completed the form.\");</script>");
            list = null;
        }else if(rank.equals("heat") && filter.equals("title")){
            list=imgDAO.title_heat(input);
        }else if(rank.equals("heat") && filter.equals("content")){
            list=imgDAO.content_heat(input);
        }
        else if(rank.equals("time") && filter.equals("content")){
            list=imgDAO.content_time(input);
        }
        else if(rank.equals("time") && filter.equals("title")){
            list=imgDAO.title_time(input);
        }else {
            list = null;
        }


        if(list!=null && list.size()>0){//如果有结果

            int totalPage = (list.size()%5)==0?list.size()/5:(list.size()/5 + 1);
            int currPage = request.getParameter("page")==null?1:Integer.parseInt(request.getParameter("page").toString());
            int back = (currPage == 1) ? 1 : currPage - 1;
            int next = (currPage == totalPage) ? totalPage : currPage + 1;


                for(int i = (currPage-1)*5;i<list.size() && i<(currPage*5)-1;i++){//int i =0;i<list.size()
                    out.print("<div class=\"image\" id=\"image\">\n" +
                            "        <div class=\"box\">\n" +
                            "            <a href=\"details.jsp?id="+list.get(i).getImageId()+"\"><img src=\"../travel-images/large/"+list.get(i).getPath()+"\" alt=\"img\" height=\"150\" width=\"200\"></a>\n" +
                            "            <div class=\"words\">\n" +
                            "                <h4>"+list.get(i).getTitle()+"</h4>\n" +
                            "                <h6>upload date:"+list.get(i).getUploadDate()+"</h6>\n" +
                            "                <p>"+list.get(i).getDescription()+"</p>\n" +
                            "            </div>\n" +
                            "        </div>\n" +
                            "    </div>");

                }


                if(rank.equals("heat") && filter.equals("title")){//根据标题热度筛选

                    out.print("<div class = \"page\"><a href=\"Search.jsp?filter=title&&rank=heat&&search="+input+"&&page="+back+"\">previous</a>");

                    boolean isfull = false;
                    if(totalPage <= 5){
                        for (int i = 1; i <= totalPage ; i++) {
                            if (i == currPage) out.print("<a href=\"Search.jsp?filter=title&&rank=heat&&search="+input+"&&page="+i+"\" style = \"color: blueviolet;\">"+i+"</a>");
                            else out.print("<a href=\"Search.jsp?filter=title&&rank=heat&&search="+input+"&&page="+i+"\">"+i+"</a>");
                        }
                    }
                    else{
                        for (int i = 1; i <= 5 ; i++) {
                            if (i == currPage) out.print("<a href=\"Search.jsp?filter=title&&rank=heat&&search="+input+"&&page="+i+"\" style = \"color: blueviolet;\">"+i+"</a>");
                            else out.print("<a href=\"Search.jsp?filter=title&&rank=heat&&search="+input+"&&page="+i+"\">"+i+"</a>");
                        }
                        out.print("...");
                    }
                    out.print("<a href=\"Search.jsp?filter=title&&rank=heat&&search="+input+"&&page="+next+"\">next</a></div>");


                }else if(rank.equals("heat") && filter.equals("content")){

                    out.print("<div class = \"page\"><a href=\"Search.jsp?filter=content&&rank=heat&&search="+input+"&&page="+back+"\">previous</a>");

                    boolean isfull = false;
                    if(totalPage <= 5){
                        for (int i = 1; i <= totalPage ; i++) {
                            if (i == currPage) out.print("<a href=\"Search.jsp?filter=content&&rank=heat&&search="+input+"&&page="+i+"\" style = \"color: blueviolet;\">"+i+"</a>");
                            else out.print("<a href=\"Search.jsp?filter=content&&rank=heat&&search="+input+"&&page="+i+"\">"+i+"</a>");
                        }
                    }
                    else{
                        for (int i = 1; i <= 5 ; i++) {
                            if (i == currPage) out.print("<a href=\"Search.jsp?filter=content&&rank=heat&&search="+input+"&&page="+i+"\" style = \"color: blueviolet;\">"+i+"</a>");
                            else out.print("<a href=\"Search.jsp?filter=content&&rank=heat&&search="+input+"&&page="+i+"\">"+i+"</a>");
                        }
                        out.print("...");
                    }
                    out.print("<a href=\"Search.jsp?filter=content&&rank=heat&&search="+input+"&&page="+next+"\">next</a></div>");

                }else if(rank.equals("time") && filter.equals("content")){

                    out.print("<div class = \"page\"><a href=\"Search.jsp?filter=content&&rank=time&&search="+input+"&&page="+back+"\">previous</a>");

                    boolean isfull = false;
                    if(totalPage <= 5){
                        for (int i = 1; i <= totalPage ; i++) {
                            if (i == currPage) out.print("<a href=\"Search.jsp?filter=content&&rank=time&&search="+input+"&&page="+i+"\" style = \"color: blueviolet;\">"+i+"</a>");
                            else out.print("<a href=\"Search.jsp?filter=content&&rank=time&&search="+input+"&&page="+i+"\">"+i+"</a>");
                        }
                    }
                    else{
                        for (int i = 1; i <= 5 ; i++) {
                            if (i == currPage) out.print("<a href=\"Search.jsp?filter=content&&rank=time&&search="+input+"&&page="+i+"\" style = \"color: blueviolet;\">"+i+"</a>");
                            else out.print("<a href=\"Search.jsp?filter=content&&rank=time&&search="+input+"&&page="+i+"\">"+i+"</a>");
                        }
                        out.print("...");
                    }
                    out.print("<a href=\"Search.jsp?filter=content&&rank=time&&search="+input+"&&page="+next+"\">next</a></div>");

                }else if(rank.equals("time") && filter.equals("title")){

                    out.print("<div class = \"page\"><a href=\"Search.jsp?filter=title&&rank=time&&search="+input+"&&page="+back+"\">previous</a>");

                    boolean isfull = false;
                    if(totalPage <= 5){
                        for (int i = 1; i <= totalPage ; i++) {
                            if (i == currPage) out.print("<a href=\"Search.jsp?filter=title&&rank=time&&search="+input+"&&page="+i+"\" style = \"color: blueviolet;\">"+i+"</a>");
                            else out.print("<a href=\"Search.jsp?filter=title&&rank=time&&search="+input+"&&page="+i+"\">"+i+"</a>");
                        }
                    }
                    else{
                        for (int i = 1; i <= 5 ; i++) {
                            if (i == currPage) out.print("<a href=\"Search.jsp?filter=title&&rank=time&&search="+input+"&&page="+i+"\" style = \"color: blueviolet;\">"+i+"</a>");
                            else out.print("<a href=\"Search.jsp?filter=title&&rank=time&&search="+input+"&&page="+i+"\">"+i+"</a>");
                        }
                        out.print("...");
                    }
                    out.print("<a href=\"Search.jsp?filter=title&&rank=time&&search="+input+"&&page="+next+"\">next</a></div>");

                }

            }
        else {
            out.print("<div class=\"feedback\">Search to get some pics...</div>");
        }

    %>


</div>



</div>


</body>
</html>