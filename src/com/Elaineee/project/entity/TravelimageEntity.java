package com.Elaineee.project.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "travelimage", schema = "travel", catalog = "")
public class TravelimageEntity {
    private int imageId;
    private String title;
    private String description;
    private Double latitude;
    private Double longitude;
    private Integer cityCode;
    private String countryRegionCodeIso;
    private Integer uid;
    private String path;
    private String content;
    private Timestamp uploadDate;

    @Id
    @Column(name = "ImageID")
    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    @Basic
    @Column(name = "Title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "Description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "Latitude")
    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    @Basic
    @Column(name = "Longitude")
    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @Basic
    @Column(name = "CityCode")
    public Integer getCityCode() {
        return cityCode;
    }

    public void setCityCode(Integer cityCode) {
        this.cityCode = cityCode;
    }

    @Basic
    @Column(name = "Country_RegionCodeISO")
    public String getCountryRegionCodeIso() {
        return countryRegionCodeIso;
    }

    public void setCountryRegionCodeIso(String countryRegionCodeIso) {
        this.countryRegionCodeIso = countryRegionCodeIso;
    }

    @Basic
    @Column(name = "UID")
    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    @Basic
    @Column(name = "PATH")
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Basic
    @Column(name = "Content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "UploadDate")
    public Timestamp getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Timestamp uploadDate){this.uploadDate = uploadDate;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TravelimageEntity that = (TravelimageEntity) o;
        return imageId == that.imageId &&
                Objects.equals(title, that.title) &&
                Objects.equals(description, that.description) &&
                Objects.equals(latitude, that.latitude) &&
                Objects.equals(longitude, that.longitude) &&
                Objects.equals(cityCode, that.cityCode) &&
                Objects.equals(countryRegionCodeIso, that.countryRegionCodeIso) &&
                Objects.equals(uid, that.uid) &&
                Objects.equals(path, that.path) &&
                Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(imageId, title, description, latitude, longitude, cityCode, countryRegionCodeIso, uid, path, content);
    }
}
