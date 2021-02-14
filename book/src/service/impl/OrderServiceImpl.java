package service.impl;

import dao.BookDao;
import dao.OrderDao;
import dao.OrderItemDao;
import dao.impl.BookDaoImpl;
import dao.impl.OrderDaoImpl;
import dao.impl.OrderItemDaoImpl;
import pojo.*;
import service.OrderService;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author xzcube
 * @date 2021/2/4 18:31
 */
public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao = new OrderDaoImpl();
    private OrderItemDao orderItemDao = new OrderItemDaoImpl();
    BookDao bookDao = new BookDaoImpl();

    @Override
    public String createOrder(Cart cart, Integer userId) {
        //生成一个唯一的订单号(时间戳 + userId)
        String orderId = System.currentTimeMillis() + "" + userId;
        //创建一个订单对象
        Order order = new Order(orderId, new Date(), cart.getTotalPrice(), 0, userId);
        //保存订单
        orderDao.saveOrder(order);

        //保存订单项(购物车中的商品项就是订单项)
        //遍历购物车中的每个商品项转换为订单项保存到数据库
        for(Map.Entry<Integer, CartItem> entry : cart.getItems().entrySet()){
            //获取每个购物车中的商品项
            CartItem cartItem = entry.getValue();
            //转换为每个订单项
            OrderItem orderItem = new OrderItem(null, cartItem.getName(), cartItem.getCount(),
                    cartItem.getPrice(), cartItem.getTotalPrice(), orderId); // 一个订单项
            //保存到数据库
            orderItemDao.saveOrderItem(orderItem);

            //更新数据库中图书的销量和库存信息
            Book book = bookDao.queryBookById(cartItem.getId());
            book.setSales(book.getSales() + cartItem.getCount()); // 新的销量值
            book.setStock(book.getStock() - cartItem.getCount()); // 新的库存数量
            bookDao.updateBook(book);
        }
        //清空购物车
        cart.clear();

        return orderId;
    }

    @Override
    public List<Order> showMyOrder(int userId) {
        List<Order> orders = orderDao.queryOrderByUserId(userId);
        return orders;
    }

    @Override
    public List<OrderItem> showOrderDetail(String orderId) {
        List<OrderItem> orderItems = orderItemDao.queryOrderItemsById(orderId);
        return orderItems;
    }
}
