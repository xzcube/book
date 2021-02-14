package test;

import dao.OrderItemDao;
import dao.impl.OrderItemDaoImpl;
import org.junit.Test;
import pojo.OrderItem;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author xzcube
 * @date 2021/2/4 18:15
 */
public class OrderItemDaoTest {
    OrderItemDao orderItemDao = new OrderItemDaoImpl();

    @Test
    public void saveOrderItem() {
        OrderItem orderItem = new OrderItem(null, "数据结构与算法", 1, new BigDecimal(100), new BigDecimal(100), "1234567890");
        OrderItem orderItem1 = new OrderItem(null, "java", 1, new BigDecimal(100), new BigDecimal(100), "1234567890");
        OrderItem orderItem2 = new OrderItem(null, "javascript", 1, new BigDecimal(100), new BigDecimal(100), "1234567890");
        orderItemDao.saveOrderItem(orderItem);
        orderItemDao.saveOrderItem(orderItem1);
        orderItemDao.saveOrderItem(orderItem2);
    }

    @Test
    public void queryOrderItemsById(){
        List<OrderItem> orderItems = orderItemDao.queryOrderItemsById("16124473724561");
        System.out.println(orderItems);
    }
}