package test;

import java.lang.reflect.Method;

/**
 * @author xzcube
 * @date 2021/1/25 20:09
 */
public class UserServletTest {
    public void login(){
        System.out.println("login方法调用了");
    }

    public void register(){
        System.out.println("register方法调用了");
    }

    public void updateUser(){
        System.out.println("updateUser方法调用了");
    }

    public void updatePassword(){
        System.out.println("updatePassword方法调用了");
    }

    public static void main(String[] args) {
        String action = "login";
        try {
            //通过action鉴别字符串，获取相应的业务方法反射对象
            Method method = UserServletTest.class.getDeclaredMethod(action);
            System.out.println(method);

            //调用目标业务，方法
            method.invoke(new UserServletTest());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
