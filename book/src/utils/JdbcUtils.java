package utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author xzcube
 * @date 2021/1/17 11:54
 */
public class JdbcUtils {
    /**
     * 通过Druid数据库连接池获取连接
     * @return
     */
    private static DataSource druidDataSource;
    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();
    static {
        Properties properties = new Properties();
        //从流中加载数据
        InputStream inputStream = JdbcUtils.class.getClassLoader().getResourceAsStream("druid.properties");
        try {
            properties.load(inputStream);
            druidDataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        Connection connection = threadLocal.get();
        if(connection == null){
            try {
                connection = druidDataSource.getConnection();
                threadLocal.set(connection);
                connection.setAutoCommit(false);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return connection;
    }

    /**
     * 提交事务，并释放连接
     */
    public static void commitAndClose(){
        Connection connection = threadLocal.get();
        if(connection != null){ // 如果不为空，说明之前使用过连接操作过数据库
            try {
                connection.commit(); // 提交事务
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }finally {
                try {
                    connection.close(); // 关闭连接
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }

        threadLocal.remove(); // 一定要执行remove操作，否则会出错（因为Tomcat服务器底层使用了线程池操作数据库）
    }

    /**
     * 回滚事务，并释放连接
     */
    public static void rollbackAndClose(){
        Connection connection = threadLocal.get();
        if(connection != null){ // 如果不为空，说明之前使用过连接操作过数据库
            try {
                connection.rollback(); // 回滚事务
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }finally {
                try {
                    connection.close(); // 关闭连接
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }

        threadLocal.remove(); // 一定要执行remove操作，否则会出错（因为Tomcat服务器底层使用了线程池操作数据库）
    }

    /*public static void closeConnection(Connection connection){
        if(connection != null) {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }*/
}
