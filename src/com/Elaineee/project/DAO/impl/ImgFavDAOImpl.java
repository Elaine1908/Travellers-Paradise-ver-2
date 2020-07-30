package com.Elaineee.project.DAO.impl;

import com.Elaineee.project.DAO.DAO;
import com.Elaineee.project.DAO.ImgFavDAO;
import com.Elaineee.project.entity.TravelimagefavorEntity;
import com.Elaineee.project.entity.TraveluserEntity;

import java.util.List;

public class ImgFavDAOImpl extends DAO<TravelimagefavorEntity> implements ImgFavDAO {
    @Override
    public List<TravelimagefavorEntity> getAll(int id) {
        String sql = "SELECT UID, ImageID FROM travelimagefavor WHERE UID=?";
        return getList(sql,id);
    }

    @Override
    public void save(TravelimagefavorEntity fav) {
        String sql = "INSERT INTO travelimagefavor (UID, ImageID) VALUES (?,?)";
        update(sql,fav.getUid(),fav.getImageId());
    }

    @Override
    public TravelimagefavorEntity get(int id) {
        return null;
    }

    @Override
    public void delete(int Imageid,int uid) {
        String sql = "DELETE FROM travelimagefavor WHERE ImageID = ? and UID = ?";
        update(sql,Imageid,uid);
    }

    public long popularity(int Imageid){//热度
        String sql = "SELECT COUNT(*) FROM travelimagefavor WHERE ImageID=?";
        return getValue(sql,Imageid);
    }

    public boolean exist(int Imageid,int uid){
        String sql ="SELECT UID, ImageID FROM travelimagefavor WHERE UID=? and ImageID=?";
        TravelimagefavorEntity travelimagefavorEntity = get(sql,uid,Imageid);
        return travelimagefavorEntity != null;
    }
}
