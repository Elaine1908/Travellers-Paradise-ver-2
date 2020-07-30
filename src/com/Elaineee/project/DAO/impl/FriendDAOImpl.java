package com.Elaineee.project.DAO.impl;
import com.Elaineee.project.DAO.DAO;
import com.Elaineee.project.DAO.FriendDAO;
import com.Elaineee.project.entity.Travelfriend;

public class FriendDAOImpl extends DAO<Travelfriend> implements FriendDAO {

    @Override
    public void update(Travelfriend travelfriend) {
        String sql = "UPDATE friends SET request=?,friends=?,UID=? WHERE UID=?";
        update(sql,travelfriend.getRequest(),travelfriend.getFriends(),travelfriend.getUid(),travelfriend.getUid());
    }

    @Override
    public Travelfriend get(int id) {
        String sql = "SELECT friends,request,UID FROM friends WHERE UID=?";
        return get(sql,id);
    }
}
