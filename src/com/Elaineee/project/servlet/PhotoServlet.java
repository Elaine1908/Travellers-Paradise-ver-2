package com.Elaineee.project.servlet;

import com.Elaineee.project.DAO.impl.ImgDAOImpl;
import com.Elaineee.project.entity.TravelimageEntity;
import com.Elaineee.project.entity.TravelimagefavorEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "PhotoServlet",value = "/photo")
public class PhotoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ImgDAOImpl imgDAO = new ImgDAOImpl();
        HttpSession session = request.getSession();
        int uid = Integer.parseInt(session.getAttribute("id").toString());
        List<TravelimageEntity> list = imgDAO.getMine(uid);
        if(!list.isEmpty()){
            for(TravelimageEntity img:list){
                String title = img.getTitle();
                String description = img.getDescription();
                if(title==null){title="WONDERLAND";}
                if(description==null){description="There's no description yet.";}

                response.getWriter().write("<div class=\"box\">\n" +
                        "    <a href=\"details.jsp?id="+img.getImageId()+"\"><img src=\"../travel-images/large/"+img.getPath()+"\" alt=\"image\"></a>\n" +
                        "    <div class=\"words\">\n" +
                        "        <h4>"+title+"</h4>\n" +
                        "        <p>"+description+"</p>\n" +
                        "    </div>\n" +
                        "    <a href=\"../ImgServlet?method=modify&&id="+img.getImageId()+"\"><button class=\"modify\">MODIFY</button></a><a href=\"../ImgServlet?method=delete&&id="+img.getImageId()+"\" class=\"confirm\"><button class=\"delete\">DELETE</button></a>\n" +
                        "</div>");
            }

            response.getWriter().write( "<script>$(document).ready(function(){\n" +
                    "    $('.confirm').click(function () {\n" +
                    "        var flag = confirm(\"Sure to delete?\");return flag;\n" +
                    "    });\n" +
                    "});</script>");

    }else {
            response.getWriter().write("<div class=\"feedback\">You haven’t uploaded any pictures. Click \"upload\" to add one!</div>");
        }
}}
