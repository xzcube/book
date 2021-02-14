package dao;

import pojo.Order;

import java.util.List;

/**
 * @author xzcube
 * @date 2021/2/4 17:52
 */
public interface OrderDao {
    /**
     * 保存订单
     * @param order
     * @return
     */
    int saveOrder(Order order);

    /**
     * 通过用户id查询用户订单
     * @param userId
     * @return
     */
    List<Order> queryOrderByUserId(int userId);
}
