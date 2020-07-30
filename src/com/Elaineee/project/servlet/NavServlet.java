package com.Elaineee.project.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "NavServlet" ,value = "/nav")
public class NavServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        if(session.getAttribute("user")!=null){

            response.getWriter().write("<a href=\"#\" class=\"user\">"+ session.getAttribute("user") + "</a>\n" +
                    "            <ul class=\"dropdown\">\n" +
                    "                <li><a href=\"upload.jsp\" id=\"upload\"><i class=\"fa fa-upload fa-fw\" aria-hidden=\"true\"></i>upload</a></li>\n" +
                    "                <li><a href=\"photo.jsp\" id=\"myPhoto\"><i class=\"fa fa-photo fa-fw\" aria-hidden=\"true\"></i>my photos</a></li>\n" +
                    "                <li><a href=\"favorites.jsp\" id=\"myFavor\"><i class=\"fa fa-heart fa-fw\" aria-hidden=\"true\"></i>my favor</a></li>\n" +
                    "                <li><a href=\"friends.jsp\" id=\"myFriend\"><i class=\"fa fa-users fa-fw\" aria-hidden=\"true\"></i>my friends</a></li>\n" +
                    "                <li><a href=\"logout.jsp\"><i class=\"fa fa-sign-out fa-fw\" aria-hidden=\"true\"></i>log out</a></li>\n" +
                    "            </ul>\n" +
                    "       \n");
        }
        else {
            response.getWriter().write("<li><a href=\"login.jsp\" class=\"user\">Log in </a><li>");
        }

        System.out.println(session.getAttribute("user"));

    }
}
