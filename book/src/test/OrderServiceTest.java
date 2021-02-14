package test;

import org.junit.Test;
import pojo.Cart;
import pojo.CartItem;
import pojo.Order;
import pojo.OrderItem;
import service.OrderService;
import service.impl.OrderServiceImpl;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author xzcube
 * @date 2021/2/4 18:30
 */
public class OrderServiceTest {

    OrderService orderService = new OrderServiceImpl();

    @Test
    public void createOrder() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1, "代码改变世界", 1, new BigDecimal(100), new BigDecimal(100)));
        cart.addItem(new CartItem(1, "代码改变世界", 1, new BigDecimal(100), new BigDecimal(100)));
        cart.addItem(new CartItem(2, "数据结构与算法", 1, new BigDecimal(90), new BigDecimal(90)));


        String order = orderService.createOrder(cart, 1);
        System.out.println("订单号为：" + order);
    }

    @Test
    public void showMyOrder(){
        List<Order> orders = orderService.showMyOrder(1);
        System.out.println(orders);
    }

    @Test
    public void showOrderDetail(){
        List<OrderItem> orderItems = orderService.showOrderDetail("16124473724561");
        System.out.println(orderItems);
    }
}