package service;

import pojo.User;

/**
 * 业务层 登录，注册，检查用户名是否存在
 * @author xzcube
 * @date 2021/1/17 20:57
 */
public interface UserService {
    /**
     * 用户注册
     * @param user
     */
    public void registerUser(User user);

    /**
     * 用户登录
     * @param user
     * @return 如果返回null，说明登录失败，返回有值，则登录成功
     */
    public User login(User user);

    /**
     * 检查用户名是否存在
     * @param username
     * @return 返回true 该用户名已存在，不可用，返回false 该用户名尚未被注册，可用
     */
    public boolean existsUser(String username);
}
