package dao;

import pojo.OrderItem;

import java.util.List;

/**
 * @author xzcube
 * @date 2021/2/4 17:53
 */
public interface OrderItemDao {
    /**
     * 保存订单项
     * @param orderItem
     * @return
     */
    int saveOrderItem(OrderItem orderItem);

    /**
     * 通过订单号查询订单详情
     * @param orderId
     * @return
     */
    List<OrderItem> queryOrderItemsById(String orderId);
}
