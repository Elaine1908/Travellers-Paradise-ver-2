package com.Elaineee.project.DAO;

import com.Elaineee.project.db.JdbcUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.util.List;

/**
 * 封装了基本的CRUD方法，供子类继承使用
 *采取 DBUtils 解决方案
 * @param <T>：当前DAO处理的实体类类型
 */
public class DAO<T> {
    //参看DAO提供实现
    //反射？？
    private QueryRunner queryRunner = new QueryRunner();
    private Class<T> clazz;

    public DAO(){//3.1.6 DAO 的测试 与 DAOImplTest 未懂
        Type superClass = getClass().getGenericSuperclass();

        if(superClass instanceof ParameterizedType){

            ParameterizedType parameterizedType = (ParameterizedType)superClass;

            Type [] typeArgs = parameterizedType.getActualTypeArguments();
            if(typeArgs != null && typeArgs.length > 0){
                if(typeArgs[0] instanceof Class){
                    clazz = (Class<T>)typeArgs[0];
                }
            }

        }
    }

    

    public T get(String sql, Object ... args){
        Connection connection = null;

        try{
            connection = JdbcUtil.getConnection();
            return queryRunner.query(connection,sql,new BeanHandler<>(clazz),args);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JdbcUtil.releaseConnection(connection);
        }

        System.out.println(clazz);
        return null;
    }

    public List<T> getList(String sql, Object ... args){
        Connection connection = null;

        try{
            connection = JdbcUtil.getConnection();
            return queryRunner.query(connection,sql,new BeanListHandler<>(clazz),args);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JdbcUtil.releaseConnection(connection);
        }
        return null;
    }


    //返回某行的某个值 或 某一列的统计值 e.g count(id)
    public <E> E getValue(String sql, Object ... args){
        Connection connection = null;

        try{
            connection = JdbcUtil.getConnection();
            return (E)queryRunner.query(connection,sql,new ScalarHandler<>(),args);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JdbcUtil.releaseConnection(connection);
        }

        return null;
    }

    //insert update delete
    public void update(String sql, Object ... args){
        Connection connection = null;
        try{
            connection = JdbcUtil.getConnection();
            queryRunner.update(connection,sql,args);

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JdbcUtil.releaseConnection(connection);
        }
    }
}
