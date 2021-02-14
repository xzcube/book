package service;

import pojo.Cart;
import pojo.Order;
import pojo.OrderItem;

import java.util.List;

/**
 * @author xzcube
 * @date 2021/2/4 18:28
 */
public interface OrderService {

    /**
     * 生成订单(保存订单和保存订单项)
     * @param cart 购物车中的数据
     * @param userId 用户id，用于区分是属于谁的订单
     * @return
     */
    public String createOrder(Cart cart, Integer userId);

    /**
     * 通过用户id查看用户订单
     * @param userId
     * @return
     */
    List<Order> showMyOrder(int userId);

    /**
     * 通过订单号查看订单详情
     * @param orderId
     * @return
     */
    List<OrderItem> showOrderDetail(String orderId);
}
