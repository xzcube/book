package test;

import org.junit.Test;
import pojo.User;
import service.UserService;
import service.impl.UserServiceImpl;

/**
 * @author xzcube
 * @date 2021/1/17 21:15
 */
public class UserServiceImplTest {
    UserService userService = new UserServiceImpl();

    @Test
    public void registerUser() {
        User user = new User(null, "admin126", "admin", "qewf@qq.com");
        userService.registerUser(user);
    }

    @Test
    public void login() {
        User user = new User(null, "admin126", "admin", "qewf@qq.com");
        User login = userService.login(user);
        if(login == null){
            System.out.println("登录失败，用户名或密码错误");
        }else {
            System.out.println("登录成功！");
        }
    }

    @Test
    public void existsUser() {
        boolean exists = userService.existsUser("admin");
        if(exists){
            System.out.println("用户名已存在");
        }else {
            System.out.println("用户名可用");
        }
    }
}