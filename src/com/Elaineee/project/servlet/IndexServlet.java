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

@WebServlet(name = "IndexServlet",value = "/index1")
public class IndexServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ImgDAOImpl imgDAO = new ImgDAOImpl();
        List<TravelimageEntity> list= imgDAO.getAll();
        for(TravelimageEntity e:list){
            response.getWriter().write("<img src=\"../travel-images/large/"+e.getPath()+"\" onclick=window.location.href=\"http://localhost:8080/PJ_war_exploded/jsp/details.jsp?id="+e.getImageId()+"\" alt=\"image\">");
        }
    }//<a href=\"details.jsp?id="+ e.getImageId()+"\">
}
