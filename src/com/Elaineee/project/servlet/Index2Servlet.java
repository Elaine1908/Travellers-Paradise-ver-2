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

@WebServlet(name = "Index2Servlet",value = "/index2")
public class Index2Servlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ImgDAOImpl imgDAO = new ImgDAOImpl();
        List<TravelimageEntity> list= imgDAO.getLatest();
        for(TravelimageEntity e:list){
            String title = e.getTitle();
            if(title==null){ title = "WONDERLAND"; }

            response.getWriter().write("<div class=\"images\">\n" +
                    "    <h4>"+title+"</h4>\n" +
                    "    <a href=\"details.jsp?id="+e.getImageId()+"\"><img src=\"../travel-images/large/"+e.getPath()+"\" alt=\"image\"></a>\n" +
                    "    <p class=\"description\">Content: "+e.getContent()+"</p>\n" +
                    "    <p class=\"description\">Upload date: "+e.getUploadDate()+"</p>\n"+
                    "</div>");
        }
    }
}
