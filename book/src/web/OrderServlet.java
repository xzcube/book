package web;

import pojo.Cart;
import pojo.Order;
import pojo.OrderItem;
import pojo.User;
import service.OrderService;
import service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author xzcube
 * @date 2021/2/4 20:31
 */
public class OrderServlet extends BaseServlet {
    OrderService orderService = new OrderServiceImpl();

    /**
     * 生成订单
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void createOrder(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // 先获取 Cart 购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        // 获取 UserId
        User loginUser = (User) req.getSession().getAttribute("user");
        if (loginUser == null) {
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
            return;
        }
        Integer userId = loginUser.getId();
        // 调用 orderService.createOrder(Cart,UserId);生成订单
        String orderId = orderService.createOrder(cart, userId);
        // req.setAttribute("orderId", orderId);
        // 请求转发到/pages/cart/checkout.jsp
        // req.getRequestDispatcher("/pages/cart/checkout.jsp").forward(req, resp);
        req.getSession().setAttribute("orderId",orderId);
        resp.sendRedirect(req.getContextPath()+"/pages/cart/checkout.jsp");
    }

    protected void showMyOrder(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User loginUser = (User) req.getSession().getAttribute("user");
        if(loginUser == null){
            resp.sendRedirect(req.getContextPath() + "/pages/user/login.jsp");
            return;
        }
        Integer userId = loginUser.getId();
        List<Order> orders = orderService.showMyOrder(userId);
        req.getSession().setAttribute("orders", orders);
        System.out.println(orders);

        resp.sendRedirect(req.getContextPath() + "/pages/order/order.jsp");
    }

    protected void showOrderDetails(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String orderId = req.getParameter("orderId");
        System.out.println(orderId);
        List<OrderItem> orderItems = orderService.showOrderDetail(orderId);
        req.getSession().setAttribute("orderItems", orderItems);

        resp.sendRedirect(req.getContextPath() + "/pages/order/orderItem.jsp");
    }
}
