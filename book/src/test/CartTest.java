package test;

import org.junit.Test;
import pojo.Cart;
import pojo.CartItem;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @author xzcube
 * @date 2021/2/3 16:20
 */
public class CartTest {

    @Test
    public void addItem() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1, "代码改变世界", 1, new BigDecimal(100), new BigDecimal(100)));
        cart.addItem(new CartItem(1, "代码改变世界", 1, new BigDecimal(100), new BigDecimal(100)));
        cart.addItem(new CartItem(2, "数据结构与算法", 1, new BigDecimal(90), new BigDecimal(90)));
        System.out.println(cart);
    }

    @Test
    public void deleteItem() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1, "代码改变世界", 1, new BigDecimal(100), new BigDecimal(100)));
        cart.addItem(new CartItem(1, "代码改变世界", 1, new BigDecimal(100), new BigDecimal(100)));
        cart.addItem(new CartItem(2, "数据结构与算法", 1, new BigDecimal(90), new BigDecimal(90)));
        cart.deleteItem(1);
        System.out.println(cart);
    }

    @Test
    public void clear() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1, "代码改变世界", 1, new BigDecimal(100), new BigDecimal(100)));
        cart.addItem(new CartItem(1, "代码改变世界", 1, new BigDecimal(100), new BigDecimal(100)));
        cart.addItem(new CartItem(2, "数据结构与算法", 1, new BigDecimal(90), new BigDecimal(90)));
        cart.deleteItem(1);
        cart.clear();
        System.out.println(cart);
    }

    @Test
    public void updateCount() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1, "代码改变世界", 1, new BigDecimal(100), new BigDecimal(100)));
        cart.addItem(new CartItem(1, "代码改变世界", 1, new BigDecimal(100), new BigDecimal(100)));
        cart.addItem(new CartItem(2, "数据结构与算法", 1, new BigDecimal(90), new BigDecimal(90)));
        cart.updateCount(1, 10);
        cart.updateCount(2, 10);
        System.out.println(cart);
    }
}