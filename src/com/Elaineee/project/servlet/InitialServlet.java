package com.Elaineee.project.servlet;

import com.Elaineee.project.DAO.impl.ImgDAOImpl;
import com.Elaineee.project.entity.TravelimageEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "InitialServlet",value = "/initial")
public class InitialServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ImgDAOImpl imgDAO = new ImgDAOImpl();
        List<TravelimageEntity> list= imgDAO.getLatest();
        for(TravelimageEntity e:list){
            String title = e.getTitle();
            if(title==null){ title = "WONDERLAND"; }

            response.getWriter().write("<div class=\"image\" id=\"image\">\n" +
                    "        <div class=\"box\">\n" +
                    "            <a href=\"details.jsp?id="+e.getImageId()+"\"><img src=\"../travel-images/large/"+e.getPath()+"\" alt=\"img\" height=\"150\" width=\"200\"></a>\n" +
                    "            <div class=\"words\">\n" +
                    "                <h4>"+e.getTitle()+"</h4>\n" +
                    "                <h6>upload date:"+e.getUploadDate()+"</h6>\n" +
                    "                <p>"+e.getDescription()+"</p>\n" +
                    "            </div>\n" +
                    "        </div>\n" +
                    "    </div>");}
    }
}
