package com.Elaineee.project.DAO;
import java.util.List;
import com.Elaineee.project.entity.TravelimageEntity;
import com.Elaineee.project.entity.TraveluserEntity;

public interface ImgDAO {//impl文件 extends DAO implements ImgDAO
    public List<TravelimageEntity> getAll();
    public void save(TravelimageEntity img);
    public TravelimageEntity get(int id);
    public void delete(int id,int uid);
}
