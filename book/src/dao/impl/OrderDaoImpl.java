package dao.impl;

import dao.OrderDao;
import pojo.Order;

import java.util.List;

/**
 * @author xzcube
 * @date 2021/2/4 17:54
 */
public class OrderDaoImpl extends BaseDao implements OrderDao {
    @Override
    public int saveOrder(Order order) {
        String sql = "insert into t_order(`order_id`,`create_time`,`price`,`status`,`user_id`) " +
                "values(?,?,?,?,?)";
        int update = update(sql, order.getOrderId(), order.getCreateTime(), order.getPrice(),
                order.getStatus(), order.getUserId());
        return update;
    }

    @Override
    public List<Order> queryOrderByUserId(int userId) {
        String sql = "select `order_id` orderId, `create_time` createTime,`price`,`status` from t_order where user_id = ?";
        List<Order> orderList = queryForList(Order.class, sql, userId);
        return orderList;
    }
}
