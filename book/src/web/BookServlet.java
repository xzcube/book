package web;

import pojo.Book;
import pojo.Page;
import service.BookService;
import service.impl.BookServiceImpl;
import utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 编写图书模块的 Web 层
 * @author xzcube
 * @date 2021/1/26 22:06
 */
public class BookServlet extends BaseServlet{
    private BookService bookService = new BookServiceImpl();

    protected void add(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 0) + 1;
        //获取请求参数，封装为book对象
        Book book = WebUtils.copyParamBean(req.getParameterMap(), new Book());

        //调用bookService.addBook()保存图书
        if(book.getName() != null && !"".equals(book.getName())){
            bookService.addBook(book);
        }

        //跳到图书列表页面
        //通过请求转发到图书列表页面，浏览器会保留最后一次请求的全部信息，刷新页面，会再次发起添加请求
        //req.getRequestDispatcher("/manager/bookServlet?action=list").forward(req, resp);
        //通过重定向来跳转页面
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=list&pageNo=" + pageNo);
    }

    protected void delete(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        //获取请求参数id
        String id = req.getParameter("id");
        int i = WebUtils.parseInt(id);
        //调用bookService.deleteBookById()方法，删除图书
        bookService.deleteBookById(i);

        //重定向到list页面
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=list&pageNo=" + req.getParameter("pageNo"));
    }

    /**
     * 获取想要修改的图书信息
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void getBook(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        //获取请求的图书编号
        String id = req.getParameter("id");
        int i = WebUtils.parseInt(id);

        //调用bookService.queryBookById查询图书
        Book book = bookService.queryBookById(i);

        //将查询到的图书保存到request域中
        req.setAttribute("book", book);

        //请求转发到图书编辑页面
        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req, resp);
    }

    protected void update(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        //获取请求参数,并封装到Book对象中
        Book book = WebUtils.copyParamBean(req.getParameterMap(), new Book());
        
        //调用bookService.updateBook()修改数据
        bookService.updateBook(book);

        //重定向到图书列表管理页面
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=list&pageNo=" + req.getParameter("pageNo"));
    }

    /**
     * 图书列表功能的实现
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void list(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        /*//1.通过BookService查询全部图书
        List<Book> books = bookService.queryBooks();

        //2.把全部图书保存到request域中
        req.setAttribute("books", books);

        //3.请求转发到/pages/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req, resp);*/
        this.page(req, resp);
    }

    /**
     * 处理分页业务
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void page(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        //1.获取请求参数:pageNo 和 pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);

        //2.调用BookService.page(pageNo, pageSize) 获得Page对象
        Page<Book> page = bookService.page(pageNo, pageSize);

        //设置后台的url地址
        page.setUrl("manager/bookServlet?action=page");

        //3.保存Page对象到request域中
        req.setAttribute("page", page);
        //4.请求转发到pages/manager/book_manager.jsp
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req, resp);
    }
}
