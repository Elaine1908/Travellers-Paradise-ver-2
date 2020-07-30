package com.Elaineee.project.DAO.impl;
import com.Elaineee.project.DAO.DAO;
import com.Elaineee.project.DAO.ImgDAO;
import com.Elaineee.project.entity.GeocontinentsEntity;
import com.Elaineee.project.entity.TravelimageEntity;
import com.Elaineee.project.entity.TraveluserEntity;

import javax.print.DocFlavor;
import java.util.List;
import java.util.zip.CheckedOutputStream;

public class ImgDAOImpl extends DAO<TravelimageEntity> implements ImgDAO {

    @Override
    public List<TravelimageEntity> getAll() {// 获取收藏次数最多的图片
        String sql = "SELECT travelimagefavor.ImageID, Count(travelimagefavor.UID) AS NumFavor, travelimage.PATH, travelimage.Title, travelimage.Description FROM travelimage JOIN travelimagefavor ON travelimagefavor.ImageID=travelimage.ImageID GROUP BY travelimagefavor.ImageID ORDER BY NumFavor DESC LIMIT 5";
        return getList(sql);
    }

    public List<TravelimageEntity> getAllPic() {//获取数据库所有图片
        String sql = "SELECT ImageID,Country_RegionName FROM geocountries_regions JOIN travelimage on geocountries_regions.ISO = travelimage.Country_RegionCodeISO";
        return getList(sql);
    }

    public List<TravelimageEntity> getLatest(){//获取最新上传照片
        String sql ="SELECT ImageID, PATH, Title, UploadDate,Content FROM travelimage ORDER BY UploadDate DESC LIMIT 6";
        return getList(sql);
    }

    public List<TravelimageEntity> content_time(String content){
        String sql ="SELECT ImageID, PATH, Title, UploadDate,Description FROM travelimage WHERE Content like '%"+content+"%' ORDER BY UploadDate DESC";
        return getList(sql);
    }

    public List<TravelimageEntity> title_time(String title){
        String sql ="SELECT ImageID, PATH, Title, UploadDate,Description FROM travelimage WHERE Title like '%"+title+"%' ORDER BY UploadDate DESC";
        return getList(sql);
    }

    public List<TravelimageEntity> content_heat(String content) {
        String sql = "SELECT travelimagefavor.ImageID, Count(travelimagefavor.UID) AS NumFavor, travelimage.PATH, travelimage.Title, travelimage.Description,travelimage.UploadDate FROM travelimage JOIN travelimagefavor ON travelimagefavor.ImageID=travelimage.ImageID WHERE Content like '%"+content+"%' GROUP BY travelimagefavor.ImageID ORDER BY NumFavor DESC";
        return getList(sql);
    }

    public List<TravelimageEntity> title_heat(String title) {
        String sql = "SELECT travelimagefavor.ImageID, Count(travelimagefavor.UID) AS NumFavor, travelimage.PATH, travelimage.Title, travelimage.Description,travelimage.UploadDate FROM travelimage JOIN travelimagefavor ON travelimagefavor.ImageID=travelimage.ImageID WHERE Title like '%"+title+"%' GROUP BY travelimagefavor.ImageID ORDER BY NumFavor DESC";
        return getList(sql);
    }

    @Override
    public void save(TravelimageEntity img) {
        String sql = "INSERT INTO travelimage(ImageID,Title,Description,CityCode,Country_RegionCodeISO,UID,PATH,Content) VALUES (?,?,?,?,?,?,?,?)";
        update(sql,img.getImageId(),img.getTitle(),img.getDescription(),img.getCityCode(),img.getCountryRegionCodeIso(),img.getUid(),img.getPath(),img.getContent());
    }

    @Override
    public TravelimageEntity get(int id) {
        String sql = "SELECT ImageID, Title, PATH, Description,UploadDate,Content FROM travelimage WHERE ImageID = ?";
        return get(sql,id);
    }

    @Override
    public void delete(int Imageid,int uid) {//删除我的照片
        String sql = "DELETE FROM travelimage WHERE ImageID = ? and UID = ?";
        update(sql,Imageid,uid);
    }

    public String country(int Imageid){//国家
        String sql = "select Country_RegionName from geocountries_regions JOIN travelimage on geocountries_regions.ISO = travelimage.Country_RegionCodeISO where ImageID = ?";
        return getValue(sql,Imageid);
    }

    public String city(int Imagid){//城市
        String sql="select AsciiName from geocities JOIN travelimage on geocities.GeoNameID = travelimage.CityCode where ImageID = ?";
        return getValue(sql,Imagid);
    }

    public int citycode(String city){
        String sql = "select GeoNameID from geocities where AsciiName = ?";
        return getValue(sql,city);
    }

    public String countrycode(String country){
        String sql = "select ISO from geocountries_regions where Country_RegionName = ?";
        return getValue(sql,country);
    }

    public List<TravelimageEntity> getMine(int uid){//获取我的照片
        String sql = "SELECT ImageID, PATH, Title, Description FROM travelimage WHERE UID=?";
        return getList(sql,uid);
    }

    public int maxID(){//获取图片最大id值
        String sql = "select max(ImageID) from travelimage";
        return getValue(sql);
    }

    public void update(TravelimageEntity img){
        String sql = "UPDATE travelimage SET Title=?,Description=?,CityCode=?,Country_RegionCodeISO=?,PATH=?,Content=? WHERE ImageID=?";
        update(sql,img.getTitle(),img.getDescription(),img.getCityCode(),img.getCountryRegionCodeIso(),img.getPath(),img.getContent(),img.getImageId());
    }



}
