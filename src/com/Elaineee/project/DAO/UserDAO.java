package com.Elaineee.project.DAO;
import com.Elaineee.project.entity.TravelimageEntity;
import com.Elaineee.project.entity.TraveluserEntity;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
    boolean LoginCheck(String username, String password) throws SQLException;
    boolean registerCheck(String username) throws SQLException;
    public List<TraveluserEntity> getAll();
    public void save(TraveluserEntity user);
    public TraveluserEntity get(int id);
    public TraveluserEntity get(String name);
    public void delete(int id);
    public void update(TraveluserEntity user);
}
