package com.Elaineee.project.servlet;

import com.Elaineee.project.DAO.ImgDAO;
import com.Elaineee.project.DAO.impl.ImgDAOImpl;
import com.Elaineee.project.entity.TravelimageEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "ImgServlet",value="/ImgServlet")
public class ImgServlet extends HttpServlet {

    private ImgDAOImpl imgDAO = new ImgDAOImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("method");
        switch (method){
            case "delete":delete(request,response);break;//删除我的照片
            case "modify":modify(request,response);break;//修改我的照片
            case "upload":upload(request,response);break;//上传我的照片
            case "update":update(request,response);break;
        }
    }

    private void upload(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        int uid = Integer.parseInt(session.getAttribute("id").toString());
        String city = request.getParameter("city");
        String country = request.getParameter("country");
        String content = request.getParameter("content");
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String path = request.getParameter("upload");
        if(country.equals("0")||city.equals("0")){
            response.getWriter().write("<script>alert(\"You haven't selected a country or city.\");history.go(-1);</script>");
        }
        else {
            TravelimageEntity img = new TravelimageEntity();
            img.setUid(uid);img.setImageId(imgDAO.maxID()+1);
            img.setContent(content);img.setTitle(title);img.setDescription(description);img.setPath(path);
            img.setCityCode(imgDAO.citycode(city));img.setCountryRegionCodeIso(imgDAO.countrycode(country));
            imgDAO.save(img);
            response.sendRedirect("/PJ_war_exploded/jsp/photo.jsp");
        }
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int Imageid= Integer.parseInt(request.getParameter("id"));
        TravelimageEntity img = imgDAO.get(Imageid);

        String city = request.getParameter("city");
        String country = request.getParameter("country");
        String content = request.getParameter("content");
        String title = request.getParameter("title");
        String description = request.getParameter("description");

        String path = request.getParameter("upload").equals("")?img.getPath():request.getParameter("upload");
        if(country.equals("0")||city.equals("0")){
            response.getWriter().write("<script>alert(\"You haven't selected a country or city.\");history.go(-1);</script>");
        }
        else {
            img.setContent(content);img.setTitle(title);img.setDescription(description);img.setPath(path);
            img.setCityCode(imgDAO.citycode(city));img.setCountryRegionCodeIso(imgDAO.countrycode(country));
            imgDAO.update(img);
            response.sendRedirect("/PJ_war_exploded/jsp/photo.jsp");
        }
    }

    private void modify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int Imageid= Integer.parseInt(request.getParameter("id"));
        TravelimageEntity img = imgDAO.get(Imageid);
        request.setAttribute("oldimage",img);
        request.getRequestDispatcher("jsp/upload.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        int uid = Integer.parseInt(session.getAttribute("id").toString());
        int Imageid = Integer.parseInt(request.getParameter("id"));
        System.out.println(Imageid);
        imgDAO.delete(Imageid,uid);
        response.sendRedirect("/PJ_war_exploded/jsp/photo.jsp");
    }

}
