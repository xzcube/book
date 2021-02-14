package service.impl;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import pojo.User;
import service.UserService;

/**
 * 业务层 登录，注册，检查用户名是否存在
 * @author xzcube
 * @date 2021/1/17 21:10
 */
public class UserServiceImpl implements UserService {
    UserDao userDao = new UserDaoImpl();
    @Override
    public void registerUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public User login(User user) {
        return userDao.queryUserByNameAndPassword(user.getUsername(), user.getPassword());
    }

    @Override
    public boolean existsUser(String username) {
        User user = userDao.queryUserByName(username);
        //user为空，说明没查到，该用户名可用
        if(user == null){
            return false;
        }
        return true;
    }
}
