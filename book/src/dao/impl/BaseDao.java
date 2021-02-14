package dao.impl;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import utils.JdbcUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * 使用 DbUtils 操作数据库
 * @author xzcube
 * @date 2021/1/17 16:32
 */
public abstract class BaseDao {
    public QueryRunner runner = new QueryRunner();
    /**
     * update() 方法用来执行：Insert\Update\Delete 语句
     * @param sql 传入的sql语句
     * @param args sql语句中占位符对应的参数
     * @return 如果修改成功，则返回影响的行数，如果失败，则返回-1
     */
    public int update(String sql, Object ...args){
        Connection connection = JdbcUtils.getConnection();
        try {
            int update = runner.update(connection, sql, args);
            return update;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException(throwables);
        }
    }

    /**
     * 执行返回一个 javaBean 的 sql 查询语句
     * @param clazz 封装结果的实现类
     * @param sql 执行的sql语句
     * @param args sql语句中占位符对应的参数
     * @param <T> 返回结果的类型
     * @return 封装了查询结果的javaBean，如果查询失败，返回null
     */
    public <T> T queryForOne(Class<T> clazz, String sql, Object ...args){
        Connection connection = JdbcUtils.getConnection();
        BeanHandler<T> handler = new BeanHandler<T>(clazz);
        try {
            T query = runner.query(connection, sql, handler, args);
            return query;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException(throwables);
        }
    }

    /**
     * 执行返回多个javaBean的sql语句
     * @param clazz 封装结果的实现类
     * @param sql 执行的sql语句
     * @param args sql语句中占位符对应的参数
     * @param <T> 返回结果的类型
     * @return 封装了返回结果的list，如果没有结果，返回null
     */
    public <T> List<T> queryForList(Class<T> clazz, String sql, Object ...args){
        Connection connection = JdbcUtils.getConnection();
        BeanListHandler<T> listHandler = new BeanListHandler<T>(clazz);
        try {
            List<T> query = runner.query(connection, sql, listHandler, args);
            return query;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException(throwables);
        }
    }

    /***
     * 执行返回单个值的sql语句
     * @param sql 待执行的sql语句
     * @param args sql语句中占位符对应的参数
     * @return 返回查询的目标
     */
    public Object queryForSingle(String sql, Object ...args){
        Connection connection = JdbcUtils.getConnection();
        ScalarHandler scalarHandler = new ScalarHandler();

        Object query = null;
        try {
            query = runner.query(connection, sql, scalarHandler, args);
            return query;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException(throwables);
        }
    }
}
