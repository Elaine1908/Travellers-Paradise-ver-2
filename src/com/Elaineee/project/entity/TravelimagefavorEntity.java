package com.Elaineee.project.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "travelimagefavor", schema = "travel", catalog = "")
public class TravelimagefavorEntity {
    private int favorId;
    private Integer uid;
    private Integer imageId;

    @Id
    @Column(name = "FavorID")
    public int getFavorId() {
        return favorId;
    }

    public void setFavorId(int favorId) {
        this.favorId = favorId;
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
    @Column(name = "ImageID")
    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TravelimagefavorEntity that = (TravelimagefavorEntity) o;
        return favorId == that.favorId &&
                Objects.equals(uid, that.uid) &&
                Objects.equals(imageId, that.imageId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(favorId, uid, imageId);
    }
}
