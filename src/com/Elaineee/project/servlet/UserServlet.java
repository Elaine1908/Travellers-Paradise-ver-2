package com.Elaineee.project.servlet;

import com.Elaineee.project.DAO.UserDAO;
import com.Elaineee.project.DAO.impl.UserDAOImpl;
import com.Elaineee.project.entity.TraveluserEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.regex.Pattern;

@WebServlet(name = "UserServlet" ,value="/UserServlet")
public class UserServlet extends HttpServlet {
    private UserDAO userDAO = new UserDAOImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("method");
        System.out.println(method);
        switch (method) {
            case "login": login(request, response);break;
            case "register": register(request, response);break;
            case "switchoff": change1(request,response);break;
            case "switchon": change2(request,response);break;
            case "query": query(request,response);break;
        }


        /**String servletPath = request.getServletPath();
         String methodName = servletPath.substring(1);
         methodName = methodName.substring(0,methodName.length()-3);
         try{
         Method method = getClass().getDeclaredMethod(methodName,HttpServletRequest.class,HttpServletResponse.class);
         method.invoke(this,request,response);
         }catch (Exception e){
         e.printStackTrace();
         //可添加跳转至error page
         }
         */
    }

    private void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        TraveluserEntity user = userDAO.get(username);
        request.setAttribute("query_user",user);
        request.getRequestDispatcher("jsp/friends.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    private void change1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        TraveluserEntity user = userDAO.get(session.getAttribute("user").toString());
        user.setState(0);
        userDAO.update(user);
    }

    private void change2(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        TraveluserEntity user = userDAO.get(session.getAttribute("user").toString());
        user.setState(1);
        userDAO.update(user);
    }


    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();

        //try 验证码
        //1.获取请求参数：CHECK_CODE_PARAM_NAME
        String paramCode = request.getParameter("CHECK_CODE_PARAM_NAME");
        //2.获取session中的CHECK_CODE_KEY属性值
        String sessionCode = (String)request.getSession().getAttribute("CHECK_CODE_KEY");
        //3.比对，若一致说明验证码正确，不一致说明验证码错误

        System.out.println(paramCode);
        System.out.println(sessionCode);
        System.out.println(sessionCode.equals(paramCode));



        if (username != null && password != null) {
            boolean login = true;
            try {
                login = userDAO.LoginCheck(username, password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (login && paramCode.equals(sessionCode)) {
                //int uid = user.getUid();
                //Cookie cookie = new Cookie("user",username);
                //cookie.setMaxAge(3600*24);
                //response.addCookie(cookie);
                TraveluserEntity user = userDAO.get(username);
                session.setAttribute("user", username);
                session.setAttribute("id", user.getUid());
                session.setMaxInactiveInterval(3600 * 24);

                //request.getRequestDispatcher("/jsp/index.jsp").forward(request,response);
                response.sendRedirect("/PJ_war_exploded/jsp/index.jsp");
            }else if(!paramCode.equals(sessionCode)){
                response.getWriter().print("<script language='javascript'>alert('Wrong captcha.');history.go(-1);</script>");
            }else {
                response.getWriter().print("<script language='javascript'>alert('Wrong username or password.');history.go(-1);</script>");
            }
        }else{
            response.getWriter().print("<script language='javascript'>alert('Wrong username or password.');history.go(-1);</script>");
        }
    }

    private void register(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String repassword = request.getParameter("repassword");
        boolean register = true;

        //try 验证码
        //1.获取请求参数：CHECK_CODE_PARAM_NAME
        String paramCode = request.getParameter("CHECK_CODE_PARAM_NAME");
        System.out.println(paramCode);
        //2.获取session中的CHECK_CODE_KEY属性值
        String sessionCode = (String)request.getSession().getAttribute("CHECK_CODE_KEY");
        //3.比对，若一致说明验证码正确，不一致说明验证码错误


        if (!Pattern.matches("[a-zA-Z][a-zA-Z0-9_\\.]{4,15}$", username)) {
            request.setAttribute("message","Illegal username.");
            //response.getWriter().print("<script>alert('Illegal username. Username should start with a character and limited within 4-15 characters.');</script>");
            request.getRequestDispatcher("/jsp/register.jsp").forward(request,response);
        }
        else if (!Pattern.matches("[a-zA-Z0-9]*[\\w\\.-]*[a-zA-Z0-9]*@([a-zA-Z0-9]*\\.)+[a-zA-Z]{2,4}$", email)) {
            request.setAttribute("message","Invalid email.");
            request.getRequestDispatcher("jsp/register.jsp").forward(request,response);
            //response.getWriter().print("<script>alert('Invalid email.');history.go(-1);</script>");
        }
        else if (!Pattern.matches("[a-zA-Z0-9]{6,12}$", password)) {
            request.setAttribute("message","Illegal password.");
            request.getRequestDispatcher("jsp/register.jsp").forward(request,response);
            //response.getWriter().print("<script>alert('Password should be limited within 6-12 characters.');history.go(-1);</script>");
        }
        else if (!password.equals(repassword)) {
            request.setAttribute("message","Password confirmed doesn't match.");
            request.getRequestDispatcher("jsp/register.jsp").forward(request,response);
            //response.getWriter().print("<script>alert('Password confirmed doesn't match.');history.go(-1);</script>");
        }
        else if(!paramCode.equals(sessionCode)){
            request.setAttribute("message","Wrong captcha.");
            request.getRequestDispatcher("jsp/register.jsp").forward(request,response);
        }else  {
            try {
                register = userDAO.registerCheck(username);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if(register){
                request.setAttribute("message","Username already exists.");
                request.getRequestDispatcher("jsp/register.jsp").forward(request,response);
                //response.getWriter().print("<script>alert('Username already exists.');history.go(-1);</script>");
            }
            else {
                TraveluserEntity user = new TraveluserEntity(username,password,email,new Timestamp(new Date().getTime()),1);
                userDAO.save(user);
                session.setAttribute("id", userDAO.get(username).getUid());
                session.setAttribute("user", username);
                session.setMaxInactiveInterval(3600 * 24);
                response.sendRedirect("/PJ_war_exploded/jsp/index.jsp");
                //session 重定向
        }
    }
}}
