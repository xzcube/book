package dao;

import pojo.User;

/**
 * @author xzcube
 * @date 2021/1/17 20:29
 */
public interface UserDao {
    /**
     * 根据用户名查询用户信息
     * @param username 用户名
     * @return 如果返回null说明没有这个用户，反之说明用户名存在
     */
    public User queryUserByName(String username);

    /**
     * 保存用户信息
     * @param user 待保存的用户
     * @return 返回-1，说明保存失败
     */
    public int saveUser(User user);

    /**
     * 根据用户名和密码查询用户信息
     * @param username 用户名
     * @param password 密码
     * @return 如果返回null,说明用户名或密码错误
     */
    public User queryUserByNameAndPassword(String username, String password);
}
