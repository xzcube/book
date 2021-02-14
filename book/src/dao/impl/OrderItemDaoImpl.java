package dao.impl;

import dao.OrderItemDao;
import pojo.OrderItem;

import java.util.List;

/**
 * @author xzcube
 * @date 2021/2/4 17:58
 */
public class OrderItemDaoImpl extends BaseDao implements OrderItemDao {
    @Override
    public int saveOrderItem(OrderItem orderItem) {
        String sql = "insert into t_order_item(`name`, `count`, `price`, `total_price`, `order_id`)" +
                "values(?, ?, ?, ?, ?)";
        int update = update(sql, orderItem.getName(), orderItem.getCount(), orderItem.getPrice(),
                orderItem.getTotalPrice(), orderItem.getOrderId());
        return update;
    }

    @Override
    public List<OrderItem> queryOrderItemsById(String orderId) {
        String sql = "SELECT `name`, `price`, `count`, `total_price` totalPrice FROM t_order_item where `order_id` = ?";
        List<OrderItem> orderItems = queryForList(OrderItem.class, sql, orderId);
        return orderItems;
    }
}
