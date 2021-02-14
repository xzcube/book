package service;

import pojo.Book;
import pojo.Page;

import java.util.List;

/**
 * @author xzcube
 * @date 2021/1/26 21:52
 */
public interface BookService {
    /**
     * 添加一本书
     * @param book
     */
    public void addBook(Book book);

    /**
     * 通过id删除一本书
     * @param id
     */
    public void deleteBookById(Integer id);

    /**
     * 修改一本书的信息
     * @param book
     */
    public void updateBook(Book book);

    /**
     * 通过id查询一本书
     * @param id
     * @return
     */
    public Book queryBookById(Integer id);

    /**
     * 查询所有书
     * @return
     */
    public List<Book> queryBooks();

    /**
     * 获得一个分页页面
     * @param pageNo 当前页码
     * @param pageSize 一页显示的数量
     * @return
     */
    Page<Book> page(int pageNo, int pageSize);

    Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max);
}
