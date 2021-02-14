package web;

import com.google.gson.Gson;
import pojo.User;
import service.UserService;
import service.impl.UserServiceImpl;
import utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * @author xzcube
 * @date 2021/1/25 15:48
 */
public class UserServlet extends BaseServlet {
    private UserService userService = new UserServiceImpl();


    protected void login(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        //1.获取请求参数
        /*String username = req.getParameter("username");
        String password = req.getParameter("password");*/

        User user = WebUtils.copyParamBean(req.getParameterMap(), new User());

        //2。判断用户是否存在
        User login = userService.login(user);
        //如果用户不存在
        if(login == null){
            //跳回到注册页面
            //把错误信息，和回显的表单项信息保存到request域中
            req.setAttribute("msg", "用户名或密码错误!");
            req.setAttribute("username", user.getUsername());
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
        }else {
            //登录成功
            //保存用户登录后的信息到session域中
            req.getSession().setAttribute("user", login);

            //跳转到登录成功的页面
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req, resp);
        }
    }


    protected void register(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        //获取服务器端的验证码
        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);

        //1.获取请求参数
        String code = req.getParameter("code");

        User user = WebUtils.copyParamBean(req.getParameterMap(), new User());

        //2.检查验证码是否正确（先固定为abcde）
        if(token.equalsIgnoreCase(code) && token != null){
            //3.检查用户名是否可用
            if(userService.existsUser(user.getUsername())){
                //不可用

                //把回显信息保存到request域中
                req.setAttribute("msg", "用户名已存在!");
                req.setAttribute("username", user.getUsername());
                req.setAttribute("email", user.getEmail());

                System.out.println("注册失败，用户名[" + user.getUsername() + "]已存在");
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
            }else {
                //可用，将信息保存到数据库中
                userService.registerUser(user);
                //跳转到注册成功页面
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req, resp);
            }
        }else {
            //把回显信息保存到request域中
            req.setAttribute("msg", "验证码错误！");
            req.setAttribute("username", user.getUsername());
            req.setAttribute("email", user.getEmail());

            System.out.println("验证码[" + code + "]错误");
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
        }
    }

    /**
     * 注销
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void logout(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        //销毁Session中的用户登录信息(或者销毁session)
        req.getSession().invalidate();

        //重定向到首页（或登录页面）
        resp.sendRedirect(req.getContextPath());
    }

    protected void ajaxExistsUsername(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html; charset=utf-8");
        // 获取请求的参数username
        String username = req.getParameter("username");
        boolean existsUser = userService.existsUser(username);

        //把返回的结果封装成为map对象
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("existsUsername", existsUser);
        Gson gson = new Gson();
        String json = gson.toJson(resultMap);

        resp.getWriter().write(json);
    }

}
