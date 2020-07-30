package com.Elaineee.project.db;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//c3p0
public class JdbcUtil {
    private final static String URL = "jdbc:mysql://localhost:3306/travel"; //若没有改动，默认 端口3306
     private final static String USER = "root";
    // 安装mysql时的用户名
    private final static String PASSWORD = "IamElaineee";
    // 安装mysql时的密码

    private static DataSource dataSource = null;
    static {
        dataSource = new ComboPooledDataSource("c3p0");
    }

    public JdbcUtil() {
        try {// 反射
             Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) { e.printStackTrace(); }
    }// 得到连接

     /**public Connection getConnection() {
        Connection conn = null; try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }return conn;
    }// 关闭结果集、语句和连接
      */

     public static Connection getConnection() throws SQLException {
         return dataSource.getConnection();
     }

     public void close(ResultSet rs, Statement st, Connection conn) {
        try {if (rs != null) { rs.close(); } if (st != null) { st.close(); }
        if (conn != null) { conn.close(); } }
        catch (SQLException e) { e.printStackTrace(); }
     }

     public static void releaseConnection(Connection connection){
             try {
                 if(connection != null){
                 connection.close();
             } }catch (Exception e) {
                 e.printStackTrace();
             }
         }
     }


