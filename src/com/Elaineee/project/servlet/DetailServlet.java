package com.Elaineee.project.servlet;

import com.Elaineee.project.DAO.ImgDAO;
import com.Elaineee.project.DAO.ImgFavDAO;
import com.Elaineee.project.DAO.impl.ImgDAOImpl;
import com.Elaineee.project.DAO.impl.ImgFavDAOImpl;
import com.Elaineee.project.entity.TravelimageEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;

@WebServlet(name = "DetailServlet",value = "/detail")
public class DetailServlet extends HttpServlet {
    private ImgFavDAOImpl imgFavDAO = new ImgFavDAOImpl();
    private ImgDAOImpl imgDAO = new ImgDAOImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int uid=0;
        if(session.getAttribute("user")!=null){uid = Integer.parseInt(session.getAttribute("id").toString());}
        int Imageid = Integer.parseInt(request.getParameter("id"));
        TravelimageEntity img = imgDAO.get(Imageid);//获取图片
        String title = img.getTitle();
        String description = img.getDescription();
        String content = img.getContent();
        Timestamp timestamp = img.getUploadDate();
        long popularity = imgFavDAO.popularity(Imageid);//获取热度
        String city = imgDAO.city(Imageid);
        String country = imgDAO.country(Imageid);

        if(title==null){title="WONDERLAND";}
        if(description==null){description="There's no description yet.";}
        if(city==null){city="unknown";}
        if(country==null){country="unknown";}

        response.getWriter().write("<div class=\"box left\">\n" +
                " <div class=\"small\">"+
                "    <img src=\"../travel-images/large/"+img.getPath()+"\" alt=\"image\">\n" +

                "      <div class=\"mask\"></div>\n" +
                "    </div>"+"    <div class=\"big\">\n" +
                "        <img src=\"../travel-images/large/"+img.getPath()+"\" alt=\"image\"/>\n" +
                "    </div>"+
                "</div>\n" +"<div class=\"pic\" style=\"background-color: #eeeeee\" style=\"padding-top:10px\">"+title+"<span class=\"photogragher\"> &nbsp;&nbsp;by Christopher</span></div>" +

                "<div class=\"right\">\n" +
                "    <table class=\"more\">\n" +
                "        <tr class=\"th\"><th>About the pic</th></tr>\n" +
                "        <tr><td>Content : "+content+"</td></tr>\n" +
                "        <tr><td>Country : "+country+"</td></tr>\n" +
                "        <tr><td>City : "+city+"</td></tr>\n" +
                "        <tr><td>Description : "+description+"</td></tr>\n" +
                "        <tr><td>Upload date : "+timestamp+"</td></tr>\n" +
                "    </table>\n" +
                "\n" +
                "    <table class=\"more\">\n" +
                "        <tr class=\"th\"><th>Like Number</th></tr>\n" +
                "        <tr><td class=\"num\">"+popularity+"</td></tr>\n" +
                "    </table></div>\n" +
                "\n");


        /**response.getWriter().write("<div class=\"left\">\n" +
                "    <img src=\"../travel-images/large/"+img.getPath()+"\" alt=\"image\">\n" +
                "    <div class=\"pic\">"+title+"<span class=\"photogragher\"> &nbsp;&nbsp;by Christopher</span></div>\n" +
                "</div>\n" +
                "\n" +
                "\n" +
                "<div class=\"right\">\n" +
                "    <table class=\"more\">\n" +
                "        <tr class=\"th\"><th>About the pic</th></tr>\n" +
                "        <tr><td>Content : "+content+"</td></tr>\n" +
                "        <tr><td>Country : "+country+"</td></tr>\n" +
                "        <tr><td>City : "+city+"</td></tr>\n" +
                "        <tr><td>Description : "+description+"</td></tr>\n" +
                "        <tr><td>Upload date : "+timestamp+"</td></tr>\n" +
                "    </table>\n" +
                "\n" +
                "    <table class=\"more\">\n" +
                "        <tr class=\"th\"><th>Like Number</th></tr>\n" +
                "        <tr><td class=\"num\">"+popularity+"</td></tr>\n" +
                "    </table>\n" +
                "\n");
                //"    <button onclick=\"alert()\"><i class=\"fa fa-heart fa-fw\" aria-hidden=\"true\"></i>  I LIKE IT!</button>\n" +
                //"</div>");
         */
        if(session.getAttribute("user")==null){
            response.getWriter().write("<button onclick=\"alert('Please login first.')\"><i class=\"fa fa-heart fa-fw\" aria-hidden=\"true\"></i>  I LIKE IT !</button>");
        }
        else if(imgFavDAO.exist(Imageid,uid)){
            response.getWriter().write("<button onclick=\"alert('already liked.')\"><i class=\"fa fa-heart fa-fw\" aria-hidden=\"true\"></i>  LIKED </button>");
        }
        else {
            response.getWriter().write("<button onclick=window.location.href=\"http://localhost:8080/PJ_war_exploded/fav?addid="+Imageid+"\"><i class=\"fa fa-heart fa-fw\" aria-hidden=\"true\"></i>  I LIKE IT ! </button>");
        }
    }
}

//注意！！如果uploaddate是null 补充填写时间