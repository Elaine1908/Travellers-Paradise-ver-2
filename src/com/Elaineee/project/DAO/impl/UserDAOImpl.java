package com.Elaineee.project.DAO.impl;
import com.Elaineee.project.DAO.DAO;
import com.Elaineee.project.DAO.UserDAO;
import com.Elaineee.project.db.JdbcUtil;
import com.Elaineee.project.entity.TravelimageEntity;
import com.Elaineee.project.entity.TraveluserEntity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

public class UserDAOImpl extends DAO<TraveluserEntity> implements UserDAO {

    public boolean LoginCheck(String username,String password) throws SQLException {
        JdbcUtil util = new JdbcUtil();
        Connection conn = util.getConnection();
        String sql1 = "select * from traveluser where UserName='"+username+"' and Pass='"+password+"'";
        String sql2 = "select * from traveluser where Email='"+username+"' and Pass='"+password+"'";
        Statement st = conn.createStatement();
        Statement st2 = conn.createStatement();
        ResultSet rs = null;
        ResultSet rs2 = null;
        try {
            rs = st.executeQuery(sql1);
            rs2 = st2.executeQuery(sql2);

            if(rs.next() || rs2.next()) {
                System.out.println(1111111);
                return true; }}
        catch (SQLException e) { e.printStackTrace(); }
        finally { util.releaseConnection(conn); }
        return false;
    }

    @Override
    public boolean registerCheck(String username) throws SQLException {
        JdbcUtil util = new JdbcUtil();
        Connection conn = util.getConnection();
        String sql1 = "select * from traveluser where UserName='"+username+"'";
        Statement st = conn.createStatement();
        ResultSet rs = null;
        try {
            rs = st.executeQuery(sql1);
            if(rs.next()) {
                System.out.println(1111111);
                return true; }}
        catch (SQLException e) { e.printStackTrace(); }
        finally { util.releaseConnection(conn); }
        return false;
    }




    @Override
    public List<TraveluserEntity> getAll() {
        String sql = "SELECT UID, UserName, Pass, DateJoined FROM traveluser";
        return getList(sql);//参考修改
    }

    @Override
    public void save(TraveluserEntity user) {
        String sql = "INSERT INTO traveluser(UserName, Pass, Email, DateJoined, State) VALUES (?,?,?,?,?)";//state 为是否显示收藏
        update(sql,user.getUserName(),user.getPass(),user.getEmail(),user.getDateJoined(),user.getState());

        TraveluserEntity newuser = get(user.getUserName());
        String sql2 = "INSERT INTO friends(UID,friends,request) VALUES (?,?,?)";
        update(sql2,newuser.getUid(),0,0);
    }


    public void update(TraveluserEntity user){
        String sql = "UPDATE traveluser SET State=? WHERE UserName=?";
        update(sql,user.getState(),user.getUserName());
    }

    @Override
    public TraveluserEntity get(int id) {
        String sql = "SELECT UID, UserName, Pass, Email, DateJoined,State FROM traveluser WHERE UID = ?";
        return get(sql,id);
    }

    @Override
    public TraveluserEntity get(String name) {
        String sql = "SELECT UID, UserName, Pass, Email, DateJoined, State FROM traveluser WHERE UserName = ?";
        return get(sql,name);
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM traveluser WHERE UID = ?";
        update(sql,id);
    }

    public long getCountWithName(String name){//待修改
        String sql = "SELECT count(id) FROM ... WHERE name = ?";
        return getValue(sql,name);
    }
}
