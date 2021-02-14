package pojo;

import java.util.List;

/**
 * @author xzcube
 * @date 2021/1/28 18:14
 */

/**
 * page是分页的模型对象
 * @param <T> 具体模块的javaBean类
 */
public class Page<T> {
    public static final Integer PAGE_SIZE = 4; // 一个页面显示的数量

    private Integer pageNo; // 当前页面
    private Integer pageTotal; // 总页码
    private Integer pageSize = PAGE_SIZE;
    private Integer pageTotalCount; // 总记录数
    private List<T> items; // 当前页面数据
    private String url; // 分页条的请求地址

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Page() {
    }

    public Page(Integer pageNo, Integer pageTotal, Integer pageTotalCount, List<T> items) {
        this.pageNo = pageNo;
        this.pageTotal = pageTotal;
        this.pageTotalCount = pageTotalCount;
        this.items = items;
    }

    public static Integer getPageSize() {
        return PAGE_SIZE;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        /*
        数据边界的有效检查
        校验url参数中的pageNo是否小于1，或者大于pageTotal
        如果小于1，则跳转到第一页，如果大于pageTotal则跳转到最后一页
         */
        if(pageNo < 1){
            pageNo = 1;
        }else if(pageNo > pageTotal){
            pageNo = pageTotal;
        }
        this.pageNo = pageNo;
    }

    public Integer getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }

    public Integer getPageTotalCount() {
        return pageTotalCount;
    }

    public void setPageTotalCount(Integer pageTotalCount) {
        this.pageTotalCount = pageTotalCount;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageNo=" + pageNo +
                ", pageTotal=" + pageTotal +
                ", pageSize=" + pageSize +
                ", pageTotalCount=" + pageTotalCount +
                ", items=" + items +
                ", url='" + url + '\'' +
                '}';
    }
}
