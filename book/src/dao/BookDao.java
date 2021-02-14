package dao;

import pojo.Book;

import java.util.List;

/**
 * @author xzcube
 * @date 2021/1/26 18:20
 */
public interface BookDao {
    /**
     * 添加一本书
     * @param book
     * @return
     */
    public int addBook(Book book);

    /**
     * 通过id删除一本书
     * @param id
     * @return
     */
    public int deleteBook(Integer id);

    /**
     * 通过书本信息删除一本书
     * @param book
     * @return
     */
    public int updateBook(Book book);

    /**
     * 通过id查询一本书
     * @param id
     * @return
     */
    public Book queryBookById(Integer id);

    /**
     * 查询多本书
     * @return
     */
    public List<Book> queryBooks();

    Integer queryForPageTotalCount();

    /**
     * 查询当前页数据
     * @param begin 当前页开始的编号
     * @param pageSize 当前页的数据量
     * @return
     */
    List<Book> queryForPageItems(int begin, int pageSize);

    /**
     * 按照价格区间查询总记录数
     * @param min
     * @param max
     * @return
     */
    Integer queryForPageTotalCountByPrice(int min, int max);

    /**
     * 按照价格区间查询每页内容
     * @param begin
     * @param pageSize
     * @param min
     * @param max
     * @return
     */
    List<Book> queryForPageItemsByPrice(int begin, int pageSize, int min, int max);
}
