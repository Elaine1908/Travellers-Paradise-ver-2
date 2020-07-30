package com.Elaineee.project.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "friends", schema = "travel", catalog = "")
public class Travelfriend {
    private int uid;
    private String friends;
    private String request;

    public Travelfriend(){ }
    public Travelfriend(int uid,String friends,String request){
        super();
        this.friends=friends;
        this.request=request;
        this.uid=uid;
    }


    @Basic
    @Column(name = "UID")
    public int getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    @Basic
    @Column(name = "friends")
    public String getFriends() {
        return friends;
    }

    public void setFriends(String f){this.friends = f;}


    @Basic
    @Column(name = "request")
    public String getRequest() {
        return request;
    }
    public void setRequest(String r){this.request = r;}



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Travelfriend that = (Travelfriend) o;
        return friends == that.friends &&
                Objects.equals(uid, that.uid) &&
                Objects.equals(request, that.request);
    }

    @Override
    public int hashCode() {
        return Objects.hash(friends, uid, request);
    }
}
