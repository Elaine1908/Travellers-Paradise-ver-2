package com.Elaineee.project.servlet;

import com.Elaineee.project.DAO.FriendDAO;
import com.Elaineee.project.DAO.UserDAO;
import com.Elaineee.project.DAO.impl.FriendDAOImpl;
import com.Elaineee.project.DAO.impl.UserDAOImpl;
import com.Elaineee.project.entity.Travelfriend;
import com.Elaineee.project.entity.TraveluserEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "MsgServlet",value = "/msg")
public class MsgServlet extends HttpServlet {
    private FriendDAO friendDAO = new FriendDAOImpl();
    private UserDAO userDAO = new UserDAOImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Travelfriend travelfriend = friendDAO.get(Integer.parseInt(session.getAttribute("id").toString()));
        String msg = travelfriend.getRequest();
        if(msg.equals("0")){
            response.getWriter().write("<div class=\"m\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;No messages.</div>");
        }else {
            String[] arr = msg.split(",");
            for(String e:arr){
                if(!e.equals("0") &&!e.equals("00")){
                    TraveluserEntity user = userDAO.get(Integer.parseInt(e));//获取发起请求的陌生人
                    response.getWriter().write("<div class=\"m\">"+ user.getUserName()+" wants to be your friend.<br><button class=\"message\"><a href=\"http://localhost:8080/PJ_war_exploded/friend?method=agree&&id="+user.getUid()+"\">agree</a></button></div>");
                }
            }
        }
    }
}
