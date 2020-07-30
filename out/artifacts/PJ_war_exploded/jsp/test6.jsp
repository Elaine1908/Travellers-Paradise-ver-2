<%@ page import="com.Elaineee.project.db.JdbcUtil" %>
<%@ page import="com.Elaineee.project.entity.TravelimageEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="com.Elaineee.project.DAO.impl.ImgDAOImpl" %><%--
  Created by IntelliJ IDEA.
  User: Elaineee
  Date: 2020/7/29 0029
  Time: 下午 23:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="test6.jsp" method="post" id="filter">
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

<%

    ImgDAOImpl imgDAO = new ImgDAOImpl();
    String input = request.getParameter("search");
    String filter = request.getParameter("filter");
    String rank = request.getParameter("rank");
    List<TravelimageEntity> list = imgDAO.title_heat(input);

    if((filter.equals("0") || rank.equals("0"))){

    }

    for(int i = 0;i<list.size();i++){//int i =0;i<list.size()
        out.print("<div class=\"image\" id=\"image\">\n" +
                "        <div class=\"box\">\n" +
                "            <a href=\"jsp/details.jsp?id="+list.get(i).getImageId()+"\"><img src=\"../travel-images/large/"+list.get(i).getPath()+"\" alt=\"img\" height=\"150\" width=\"200\"></a>\n" +
                "            <div class=\"words\">\n" +
                "                <h4>"+list.get(i).getTitle()+"</h4>\n" +
                "                <h6>upload date:"+list.get(i).getUploadDate()+"</h6>\n" +
                "                <p>"+list.get(i).getDescription()+"</p>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "    </div>");}


%>
</body>
</html>
