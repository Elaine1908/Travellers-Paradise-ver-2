package com.Elaineee.project.entity;

import com.Elaineee.project.db.JdbcUtil;

import javax.persistence.*;
import java.sql.*;
import java.util.Objects;

@Entity
@Table(name = "traveluser", schema = "travel", catalog = "")
public class TraveluserEntity {
    private int uid;
    private String email;
    private String userName;
    private String pass;
    private Integer state;
    private Timestamp dateJoined;
    private Timestamp dateLastModified;

    public TraveluserEntity(){};

    public TraveluserEntity(String username, String password, String email, Timestamp timestamp, int i) {
        super();
        this.email = email;
        this.pass=password;
        this.userName=username;
        this.dateJoined=timestamp;
        this.state=i;
    }

    @Id
    @Column(name = "UID")
    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    @Basic
    @Column(name = "Email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "UserName")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "Pass")
    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Basic
    @Column(name = "State")
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Basic
    @Column(name = "DateJoined")
    public Timestamp getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(Timestamp dateJoined) {
        this.dateJoined = dateJoined;
    }

    @Basic
    @Column(name = "DateLastModified")
    public Timestamp getDateLastModified() {
        return dateLastModified;
    }

    public void setDateLastModified(Timestamp dateLastModified) {
        this.dateLastModified = dateLastModified;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TraveluserEntity that = (TraveluserEntity) o;
        return uid == that.uid &&
                Objects.equals(email, that.email) &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(pass, that.pass) &&
                Objects.equals(state, that.state) &&
                Objects.equals(dateJoined, that.dateJoined) &&
                Objects.equals(dateLastModified, that.dateLastModified);
    }



    @Override
    public int hashCode() {
        return Objects.hash(uid, email, userName, pass, state, dateJoined, dateLastModified);
    }

}
