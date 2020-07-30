package com.Elaineee.project.DAO;
import com.Elaineee.project.entity.TravelimageEntity;
import com.Elaineee.project.entity.TravelimagefavorEntity;
import com.Elaineee.project.entity.TraveluserEntity;

import java.util.List;

public interface ImgFavDAO {
    public List<TravelimagefavorEntity> getAll(int id);
    public void save(TravelimagefavorEntity fav);
    public TravelimagefavorEntity get(int id);
    public void delete(int Imageid,int uid);
}
