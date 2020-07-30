package com.Elaineee.project.servlet;

import com.Elaineee.project.DAO.impl.ImgDAOImpl;
import com.Elaineee.project.DAO.impl.ImgFavDAOImpl;
import com.Elaineee.project.DAO.impl.UserDAOImpl;
import com.Elaineee.project.entity.TravelimageEntity;
import com.Elaineee.project.entity.TravelimagefavorEntity;
import com.Elaineee.project.entity.TraveluserEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "FavorServlet",value = "/fav")
public class FavorServlet extends HttpServlet {
    private ImgFavDAOImpl imgFavDAO = new ImgFavDAOImpl();
    private ImgDAOImpl imgDAO = new ImgDAOImpl();
    private UserDAOImpl userDAO = new UserDAOImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if(request.getParameter("id")!=null){//删除图片
            System.out.println("aaaaaaaaa");
            System.out.println(Integer.parseInt(request.getParameter("id")));

            imgFavDAO.delete(Integer.parseInt(request.getParameter("id")),Integer.parseInt(session.getAttribute("id").toString()));
            response.sendRedirect("/PJ_war_exploded/jsp/favorites.jsp");
        }
        else if(request.getParameter("addid")!=null){//添加收藏
            TravelimagefavorEntity travelimagefavorEntity = new TravelimagefavorEntity();
            travelimagefavorEntity.setImageId(Integer.parseInt(request.getParameter("addid")));
            travelimagefavorEntity.setUid(Integer.parseInt(session.getAttribute("id").toString()));
            imgFavDAO.save(travelimagefavorEntity);
            response.sendRedirect("/PJ_war_exploded/jsp/favorites.jsp");
        }
        else {
            showFav(request,response);//自己的收藏
        }
    }



    private void showFav(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        if(session.getAttribute("user")!=null){
            List<TravelimagefavorEntity> imgFavList = imgFavDAO.getAll(Integer.parseInt(session.getAttribute("id").toString()));
            if(!imgFavList.isEmpty()){
                for(TravelimagefavorEntity imgFav:imgFavList){
                    int ImgID = (int)imgFav.getImageId();
                    TravelimageEntity img = imgDAO.get(ImgID);
                    String title = img.getTitle();
                    String description = img.getDescription();
                    if(title==null){title="WONDERLAND";}
                    if(description==null){description="There's no description yet.";}

                    response.getWriter().write("<div class=\"box\" id=\""+ title+"\">\n" +
                            "    <a href=\"details.jsp?id="+ ImgID+"\"><img src=\"../travel-images/large/"+ img.getPath()+"\" alt=\"img\"></a>\n" +
                            "    <div class=\"words\">\n" +
                            "        <h4>"+title+"</h4>\n" +
                            "        <p>"+description+"</p>\n" +
                            "    </div>\n" +
                            "    <a href=\"../fav?id="+ImgID+"\" class=\"confirm\"><button>DELETE</button></a>\n" +
                            "</div>");
                }

                response.getWriter().write( "<script>$(document).ready(function(){\n" +
                        "    $('.confirm').click(function () {\n" +
                        "        var flag = confirm(\"Sure to delete?\");return flag;\n" +
                        "    });\n" +
                        "});</script>");
            }
            else {
                response.getWriter().write("<div class=\"feedback\">You haven't collected any photos yet. Go and pick what you like!</div>");
            }
        }
        else {
            response.getWriter().write("Please first login.");
        }
        request.setAttribute("collector",session.getAttribute("user"));
    }
}
