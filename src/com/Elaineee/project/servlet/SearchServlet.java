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

@WebServlet(name = "SearchServlet",value = "/search")
public class SearchServlet extends HttpServlet {
    private ImgDAOImpl imgDAO = new ImgDAOImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String input = request.getParameter("search");
        String filter = request.getParameter("filter");
        String rank = request.getParameter("rank");
        List<TravelimageEntity> list=imgDAO.title_time(input);//初始值

        if(request.getParameter("method")==null){//第一次访问
            if((filter.equals("0") || rank.equals("0"))){
                System.out.println(0);
                response.getWriter().write("<script>alert(\"You haven't completed the form.\");history.go(-1);</script>");
            }
            else {
                if((rank.equals("heat") && filter.equals("title"))){
                    list=imgDAO.title_heat(input);
                    request.setAttribute("method","titleheat");
                }
                else if(rank.equals("heat") && filter.equals("content")){
                    list=imgDAO.content_heat(input);
                    request.setAttribute("method","contentheat");
                }
                else if(rank.equals("time") && filter.equals("content")){
                    list=imgDAO.content_time(input);
                    request.setAttribute("method","contenttime");
                }
                else {
                    list=imgDAO.title_time(input);
                    request.setAttribute("method","titletime");
                }
        }
        }
        else {
            if(request.getParameter("method").equals("titleheat")){
                list=imgDAO.title_heat(input);
                int page = Integer.parseInt(request.getParameter("page"));
                request.setAttribute("result",list);
                request.getRequestDispatcher("/jsp/Search.jsp?method=titleheat&&page="+page).forward(request,response);

            }
            else if(rank.equals("heat") && filter.equals("content")){
                list=imgDAO.content_heat(input);
                request.setAttribute("method","contentheat");
            }
            else if(rank.equals("time") && filter.equals("content")){
                list=imgDAO.content_time(input);
                request.setAttribute("method","contenttime");
            }
            else {
                list=imgDAO.title_time(input);
                request.setAttribute("method","titletime");
            }
        }

        //request.setAttribute("result",list);
        //request.getRequestDispatcher("/jsp/Search.jsp").forward(request,response);


    }
}

/**
 if((filter.equals("0") || rank.equals("0"))){
 System.out.println(0);
 response.getWriter().write("<script>alert(\"You haven't completed the form.\");history.go(-1);</script>");
 }
 else {
 if((rank.equals("heat") && filter.equals("title")) || request.getAttribute("method").equals("titleheat")){
 list=imgDAO.title_heat(input);
 request.setAttribute("method","titleheat");
 }
 else if(rank.equals("heat") && filter.equals("content")){
 list=imgDAO.content_heat(input);
 request.setAttribute("method","contentheat");
 }
 else if(rank.equals("time") && filter.equals("content")){
 list=imgDAO.content_time(input);
 request.setAttribute("method","contenttime");
 }
 else {
 list=imgDAO.title_time(input);
 request.setAttribute("method","titletime");
 }
 request.setAttribute("result",list);
 request.getRequestDispatcher("/jsp/Search.jsp").forward(request,response);
 }
*/