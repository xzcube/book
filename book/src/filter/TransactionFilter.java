package filter;

import utils.JdbcUtils;

import javax.servlet.*;
import java.io.IOException;

/**
 * 通过Filter过滤器来给所有Servlet方法加上try-catch，进行事务管理
 * @author xzcube
 * @date 2021/2/9 21:26
 */
public class TransactionFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            filterChain.doFilter(servletRequest, servletResponse);
            JdbcUtils.commitAndClose(); // 没有异常，直接提交
        } catch (Exception e) {
            JdbcUtils.rollbackAndClose(); // 出现异常，回滚事务
            e.printStackTrace();
            throw new RuntimeException(e); // 把异常统一抛给tomcat，进行展示
        }
    }

    @Override
    public void destroy() {

    }
}
