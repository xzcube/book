package web;

import com.google.gson.Gson;
import pojo.Book;
import pojo.Cart;
import pojo.CartItem;
import service.BookService;
import service.impl.BookServiceImpl;
import utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xzcube
 * @date 2021/2/3 17:35
 */
public class CartServlet extends BaseServlet{
    private BookService bookService = new BookServiceImpl();

    /**
     * 加入购物车
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=utf-8");

        //获取请求的参数：商品编号
        int id = WebUtils.parseInt(req.getParameter("id"));
        //调用bookService.queryBookById(id)，得到图书信息
        Book book = bookService.queryBookById(id);
        //把图书信息转换为CartItem商品项
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());

        // 调用Cart.item(cartItem)添加商品项
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        //判断Session中是否有购物车
        if(cart == null){
            //如果没有，则创建一个购物车对象，放入Session中
            cart = new Cart();
            req.getSession().setAttribute("cart", cart);
        }

        cart.addItem(cartItem);
        System.out.println(cart);

        // 重定向回原来商品所在地址页面
        String referer = req.getHeader("Referer"); // 获取发送请求的地址
        //最后一个添加的商品名称
        req.getSession().setAttribute("lastName", cartItem.getName());
        resp.sendRedirect(referer);
    }

    /**
     * 根据商品编号删除购物车中的数据
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取商品编号
        int id = WebUtils.parseInt(req.getParameter("id"));
        //获取购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if(cart != null){
            //购物车不为空
            cart.deleteItem(id);
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }

    /**
     * 清空购物车
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart = (Cart) req.getSession().getAttribute("cart");

        if(cart != null){
            //清空购物车
            cart.clear();
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }

    /**
     * 修改购物车中的商品数量
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求参数：商品数量，商品编号
        int count = WebUtils.parseInt(req.getParameter("count"));
        int id = WebUtils.parseInt(req.getParameter("id"));

        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if(cart != null){
            //修改商品数量
            cart.updateCount(id, count);
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }

    /**
     * 使用 AJAX 修改把商品添加到购物车
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void ajaxAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=utf-8");

        //获取请求的参数：商品编号
        int id = WebUtils.parseInt(req.getParameter("id"));
        //调用bookService.queryBookById(id)，得到图书信息
        Book book = bookService.queryBookById(id);
        //把图书信息转换为CartItem商品项
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());

        // 调用Cart.item(cartItem)添加商品项
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        //判断Session中是否有购物车
        if(cart == null){
            //如果没有，则创建一个购物车对象，放入Session中
            cart = new Cart();
            req.getSession().setAttribute("cart", cart);
        }

        cart.addItem(cartItem);
        System.out.println(cart);

        // 重定向回原来商品所在地址页面
        String referer = req.getHeader("Referer"); // 获取发送请求的地址
        //最后一个添加的商品名称
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("totalCount", cart.getTotalCount());
        resultMap.put("lastItem", cartItem.getName());
        Gson gson = new Gson();
        String resultMapJsonString = gson.toJson(resultMap);
        resp.getWriter().write(resultMapJsonString);
    }
}
