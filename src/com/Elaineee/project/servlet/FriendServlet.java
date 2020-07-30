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

@WebServlet(name = "FriendServlet",value = "/friend")
public class FriendServlet extends HttpServlet {
    private FriendDAO friendDAO = new FriendDAOImpl();
    private UserDAO userDAO = new UserDAOImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("method");
        System.out.println(method);
        switch (method) {
            case "add": add(request,response);break;
            case "agree":agree(request,response);break;
            case "friend":friend(request,response);break;
        }
    }



    private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {// 添加别人为好友
        HttpSession session = request.getSession();

        String id = request.getParameter("id");
        Travelfriend travelfriend = friendDAO.get(Integer.parseInt(id));

        String friend = travelfriend.getFriends();
        String req = travelfriend.getRequest();



        if(id.equals(session.getAttribute("id").toString())){//自己不能加自己
            request.setAttribute("message","You cann't add yourself.");
            request.getRequestDispatcher("jsp/friends.jsp").forward(request,response);
        }
        else if(friend.contains(session.getAttribute("id").toString())){//已经互为好友

            request.setAttribute("message","Already friends.");
            request.getRequestDispatcher("jsp/friends.jsp").forward(request,response);
        }
        else if(req.contains(session.getAttribute("id").toString())){//已经发送过好友请求

            request.setAttribute("message","Request already sent.");
            request.getRequestDispatcher("jsp/friends.jsp").forward(request,response);
        }
        else if(req.equals("0")){//对方目前尚无好友
            String news = session.getAttribute("id").toString();
            Travelfriend travelfriend1 = new Travelfriend(Integer.parseInt(id),travelfriend.getFriends(),news);
            friendDAO.update(travelfriend1);
            request.setAttribute("message","Added!.");
            request.getRequestDispatcher("jsp/friends.jsp").forward(request,response);
        }
        else {
            String news = req+","+session.getAttribute("id").toString();
            Travelfriend travelfriend1 = new Travelfriend(Integer.parseInt(id),travelfriend.getFriends(),news);
            friendDAO.update(travelfriend1);
            request.setAttribute("message","Added!.");
            request.getRequestDispatcher("jsp/friends.jsp").forward(request,response);
        }
    }

    private void agree(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        String id = request.getParameter("id");//获取对方的id
        Travelfriend me = friendDAO.get(Integer.parseInt(session.getAttribute("id").toString()));
        //获取自己的好友表
        Travelfriend him = friendDAO.get(Integer.parseInt(id));
        //获取对方的好友表
        String hisfriend = him.getFriends();
        String myfriend = me.getFriends();
        String req = me.getRequest();
        StringBuilder strBuilder = new StringBuilder(req);
        if(Integer.parseInt(id)>=10){//id为双位数
            strBuilder.setCharAt(req.indexOf(id), '0');
            strBuilder.setCharAt(req.indexOf(id)+1, '0');
        }else {
            strBuilder.setCharAt(req.indexOf(id), '0');
        }

         if(myfriend.equals("0")){//我目前尚无好友
            Travelfriend travelfriend1 = new Travelfriend(Integer.parseInt(session.getAttribute("id").toString()),id,check(strBuilder.toString()));
            friendDAO.update(travelfriend1);
        }
        else {
            String news = myfriend+","+id;
            Travelfriend travelfriend1 = new Travelfriend(Integer.parseInt(session.getAttribute("id").toString()),news,check(strBuilder.toString()));
            friendDAO.update(travelfriend1);
        }

        if(hisfriend.equals("0")){//ta目前尚无好友
            Travelfriend travelfriend1 = new Travelfriend(Integer.parseInt(id),session.getAttribute("id").toString(),him.getRequest());
            friendDAO.update(travelfriend1);
        }
        else {
            String news = hisfriend+","+session.getAttribute("id").toString();
            Travelfriend travelfriend1 = new Travelfriend(Integer.parseInt(id),news,him.getRequest());
            friendDAO.update(travelfriend1);
        }

        request.getRequestDispatcher("jsp/friends.jsp").forward(request,response);

    }

    private String check(String s){
        String[] arr = s.split(",");
        for(String e:arr){
            if(!e.equals("0") && !e.equals("00")){
                return s;
            }
        }
        return "0";
    }


    private void friend(HttpServletRequest request, HttpServletResponse response) throws IOException {//展示我的好友列表

        HttpSession session = request.getSession();
        Travelfriend travelfriend = friendDAO.get(Integer.parseInt(session.getAttribute("id").toString()));
        String msg = travelfriend.getFriends();
        if(msg.equals("0")){
            response.getWriter().write("<div class=\"m\">You don't have any friends.</div>");
        }else {
            String[] arr = msg.split(",");
            response.getWriter().write("<table>    <tr>\n" +
                    "        <th>username</th>\n" +
                    "        <th>email</th>\n" +
                    "        <th>registration date</th>\n" +
                    "    </tr>");
            for(String e:arr){
                TraveluserEntity user = userDAO.get(Integer.parseInt(e));
                response.getWriter().write("<tr><td><a href=\"http://localhost:8080/PJ_war_exploded/jsp/favorites-f.jsp?friend="+user.getUserName()+"\">"+ user.getUserName()+"</a></td><td><a href=\"http://localhost:8080/PJ_war_exploded/jsp/favorites-f.jsp?friend="+user.getUserName()+"\">"+ user.getEmail()+"</a></td><td>"+ user.getDateJoined()+"</td></tr>");
            }
            response.getWriter().write("</table>");
        }
    }
}

/*
    <tr>
        <th>username</th>
        <th>email</th>
        <th>registration date</th>
    </tr>
    <tr>
        <td><a href="favorites.jsp">luisg</a></td>
        <td>luisg@hotmail.com</td>
        <td>2000-01-01</td>
    </tr>
    </table>
 */

/*

        <%
            List<TraveluserEntity> strangers = (List<TraveluserEntity>)request.getAttribute("query_stranger");
            if(!strangers.isEmpty()){
                for(TraveluserEntity e:strangers){
        %>
        <p><%= e.getUserName()%> wants to be your friend. <button><a href="">agree</a></button> </p>
        <%}}else{%>
        <p>No messages.</p>
        <%
            }
        %>

 */
