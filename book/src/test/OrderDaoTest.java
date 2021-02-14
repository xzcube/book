package test;

import dao.OrderDao;
import dao.impl.OrderDaoImpl;
import org.junit.Test;
import pojo.Order;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author xzcube
 * @date 2021/2/4 18:02
 */
public class OrderDaoTest {
    OrderDao orderDao = new OrderDaoImpl();

    @Test
    public void saveOrder() {
        Order order = new Order("1234567890", new Date(), new BigDecimal(100), 0, 1);
        orderDao.saveOrder(order);
    }

    @Test
    public void queryOrderByUserId(){
        List<Order> orders = orderDao.queryOrderByUserId(1);
        System.out.println(orders);
    }
}