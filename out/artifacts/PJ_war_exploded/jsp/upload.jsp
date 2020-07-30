<%@ page import="com.Elaineee.project.entity.TravelimageEntity" %>
<%@ page import="com.Elaineee.project.DAO.impl.CountryDAOImpl" %>
<%@ page import="com.Elaineee.project.entity.GeocountriesRegionsEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="com.Elaineee.project.DAO.impl.ImgDAOImpl" %><%--
  Created by IntelliJ IDEA.
  User: Elaineee
  Date: 2020/7/13 0013
  Time: 上午 0:33
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
    <title>Upload</title>
    <link href="<%=basePath%>/css/reset.css" rel="stylesheet" type="text/css" >
    <link href="<%=basePath%>/css/upload.css" rel="stylesheet" type="text/css" >
    <link href="<%=basePath%>/css/nav.css" rel="stylesheet" type="text/css" >
    <link rel="stylesheet" href="<%=basePath%>/font-awesome-4.7.0/css/font-awesome.min.css">
    <script src="<%=basePath%>/js/upload.js"></script>
    <script src="<%=basePath%>/js/highlight.js"></script>
    <script src="<%=basePath%>/jquery-3.5.1.min.js"></script>
</head>
<body>

<div id="nav">
    <div class="div-left">
        <ul>
            <li><a href="<%=basePath%>jsp/index.jsp">Home</a></li>
            <li><a href="<%=basePath%>jsp/Search.jsp">Search</a></li>
            <li><a href="#" class="user"><%=request.getSession().getAttribute("user")%></a>
                <ul class="dropdown">
                    <li><a href="<%=basePath%>jsp/upload.jsp" id="upload"><i class="fa fa-upload fa-fw" aria-hidden="true"></i>upload</a></li>
                    <li><a href="<%=basePath%>jsp/photo.jsp"><i class="fa fa-photo fa-fw" aria-hidden="true"></i>my photos</a></li>
                    <li><a href="<%=basePath%>jsp/favorites.jsp"><i class="fa fa-heart fa-fw" aria-hidden="true"></i>my favor</a></li>
                    <li><a href="<%=basePath%>jsp/friends.jsp"><i class="fa fa-users fa-fw" aria-hidden="true"></i>my friends</a></li>
                    <li><a href="<%=basePath%>jsp/logout.jsp"><i class="fa fa-sign-in fa-fw" aria-hidden="true"></i>log out</a></li>
                </ul>
            </li>
        </ul><!--行内元素百搭标记-->
    </div>
</div>

<div class="upload">UPLOAD</div>
<div id="preview" style="width:320px;height:200px;">

<%
    ImgDAOImpl imgDAO = new ImgDAOImpl();
    CountryDAOImpl countryDAO = new CountryDAOImpl();
    List<GeocountriesRegionsEntity> list = countryDAO.getAllCountry();

%>

<%
    TravelimageEntity img = (TravelimageEntity)request.getAttribute("oldimage");
    String title; String description;int id=0;
    if(img!=null){//modify
        id=img.getImageId();

        out.print("<img src=\""+basePath+"/travel-images/large/"+img.getPath()+"\" alt=\"image\" width=\"320\" height=\"200\"></img>\n" +
                "        </div>\n" +
                "<div class=\"input\">\n" +
                "    <form action=\""+basePath+"/ImgServlet?method=update&&id="+img.getImageId()+"\" method=\"post\">\n" +
                "    <input id=\"modify\" class=\"img\" name=\"upload\" type=\"file\" onchange=\"previewImage(this,320,200)\" accept=\"image/*\" />\n" +
                "    \n" +
                "   <div class=\"border\">\n" +
                "    <p>\n" +
                "    Content:\n" +
                "    <select name=\"content\" class=\"content\">\n" +
                "        <option value=\"city\">City</option>\n" +
                "        <option value=\"people\">People</option>\n" +
                "        <option value=\"scenery\" selected>Scenery</option>\n" +
                "        <option value=\"wonder\">Wonder</option>\n" +
                "        <option value=\"animal\">Animal</option>\n" +
                "        <option value=\"building\">Building</option>\n" +
                "        <option value=\"other\">Other</option>\n" +
                "    </select></p><br>\n" +
                "        <p>Title of the pic:<br>" +
                "            <input type=\"text\" name=\"title\" class=\"text\" value=\""+img.getTitle()+"\" required>\n" +
                "        </p>\n" +
                "        <p>Description:<br>\n" +
                "            <textarea type=\"text\" name=\"description\" required>"+img.getDescription()+"</textarea>\n" +
                "        </p>\n" +
                "        <p>Country:<br>\n" +
                "            <select name=\"country\" class=\"country\" onchange=\"set_city(this,this.form.city)\" onblur=\"set_city(this,this.form.city)\">\n" +
                "                <option value=\"0\">---select a country---</option>");

        for(GeocountriesRegionsEntity e:list){
            if(e.getCountry_RegionName().equals(imgDAO.country(img.getImageId()))){
                 out.print("<option value=\""+e.getCountry_RegionName()+"\" selected>"+e.getCountry_RegionName()+"</option>");
            }else {
                out.print("<option value=\""+e.getCountry_RegionName()+"\">"+e.getCountry_RegionName()+"</option>");
            }
        }

        out.print("</select></p><script src=\""+basePath+"/js/select.js\"></script>");

        out.print("<p>City:<br>\n" +
                "<select name=\"city\"><option value=\"0\">---select a city---</option></select></p>\n" +
                "<p><button name=\"Submit\" class=\"confirm\">Modify</button></p>" +
                "</div></form></div>");


        out.print( "<script>$(document).ready(function(){\n" +
                        "    $('.confirm').click(function () {\n" +
                        "        var flag = confirm(\"Sure to submit?\");return flag;\n" +
                        "    });\n" +
                        "});</script>");
    }else {
        //upload
        out.print("</div>\n" +
                "<div class=\"input\">\n" +
                "    <form action=\" http://localhost:8080/PJ_war_exploded/ImgServlet?method=upload\" method=\"post\">\n" +
                "    <input id=\"modify\" class=\"img\" name=\"upload\" type=\"file\" onchange=\"previewImage(this,320,200)\" accept=\"image/*\" required/>\n" +
                "    \n" +
                "   <div class=\"border\">\n" +
                "    <p>\n" +
                "    Content:\n" +
                "    <select name=\"content\" class=\"content\">\n" +
                "        <option value=\"city\">City</option>\n" +
                "        <option value=\"people\">People</option>\n" +
                "        <option value=\"scenery\" selected>Scenery</option>\n" +
                "        <option value=\"wonder\">Wonder</option>\n" +
                "        <option value=\"animal\">Animal</option>\n" +
                "        <option value=\"building\">Building</option>\n" +
                "        <option value=\"other\">Other</option>\n" +
                "    </select></p><br>\n" +
                "        <p>Title of the pic:<br>" +
                "            <input type=\"text\" name=\"title\" class=\"text\" required>\n" +
                "        </p>\n" +
                "        <p>Description:<br>\n" +
                "            <textarea type=\"text\" name=\"description\" required></textarea>\n" +
                "        </p>\n" +
                "        <p>Country:<br>\n" +
                "            <select name=\"country\" class=\"country\" onchange=\"set_city(this,this.form.city)\">\n" +
                "                <option value=\"0\">---select a country---</option>");

          for(GeocountriesRegionsEntity e:list){
                out.print("<option value=\""+e.getCountry_RegionName()+"\">"+e.getCountry_RegionName()+"</option>");
        }

        out.print("</select></p><script src=\""+basePath+"/js/select.js\"></script>");


        out.print("<p>City:<br>\n" +
                "<select name=\"city\"><option value=\"0\">---select a city---</option></select></p>\n" +
                "<p><button name=\"Submit\" class=\"confirm\">Upload</button></p>" +
                "</div></form></div>");

        out.print( "<script>$(document).ready(function(){\n" +
                        "    $('.confirm').click(function () {\n" +
                        "        var flag = confirm(\"Sure to submit?\");return flag;\n" +
                        "    });\n" +
                        "});</script>");
    }
%>





<footer><span class="copyright">Copyright © 2020 Elaine1908 All Right Reserved ICP:19302010075</span></footer>
</body>
</html>
