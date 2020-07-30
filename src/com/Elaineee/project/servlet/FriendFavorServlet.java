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

@WebServlet(name = "FriendFavorServlet",value = "/fav2")
public class FriendFavorServlet extends HttpServlet {
    private ImgFavDAOImpl imgFavDAO = new ImgFavDAOImpl();
    private ImgDAOImpl imgDAO = new ImgDAOImpl();
    private UserDAOImpl userDAO = new UserDAOImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            friendFav(request,response);//他人的收藏
    }

    private void friendFav(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("yesssssssss");
        String name = request.getParameter("friend");
        TraveluserEntity friend = userDAO.get(name);
        if(friend.getState()==1){
            List<TravelimagefavorEntity> imgFavList = imgFavDAO.getAll(friend.getUid());
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
                            "</div>");
                }
            }
            else {
                response.getWriter().write("<div class=\"feedback\">Your friend hasn't collected any photos yet.</div>");
            }

        }else {
            response.getWriter().write("<div class=\"feedback\">Ops!Your friend doesn't want to show his/her favorites.</div>");
        }
        request.setAttribute("collector",friend.getUserName());
    }
}
