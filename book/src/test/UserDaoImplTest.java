package test;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import org.junit.Test;
import pojo.User;

import static org.junit.Assert.*;

/**
 * @author xzcube
 * @date 2021/1/17 20:49
 */
public class UserDaoImplTest {

    @Test
    public void queryUserByName() {
        UserDao userDao = new UserDaoImpl();
        User admin = userDao.queryUserByName("admin123");
        if(admin == null){
            System.out.println("用户名可用");
        }else {
            System.out.println("用户名已存在");
        }
    }

    @Test
    public void saveUser() {
        User user = new User(null, "admin123", "admin", "atguigu@126.com");
        UserDao userDao = new UserDaoImpl();
        int i = userDao.saveUser(user);
        System.out.println(i);
    }

    @Test
    public void queryUserByNameAndPassword() {
        UserDao userDao = new UserDaoImpl();
        User user = userDao.queryUserByNameAndPassword("admin123", "admin");
        if(user == null){
            System.out.println("登录失败，用户名或密码错误");
        }else {
            System.out.println("登录成功");
        }
    }
}