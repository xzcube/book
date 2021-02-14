package test;

import org.junit.Test;
import utils.JdbcUtils;

/**
 * @author xzcube
 * @date 2021/1/17 16:05
 */
public class JdbcUtilsTest {
    @Test
    public void testJdbcUtils(){
        for (int i = 0; i < 10; i++) {
            System.out.println(JdbcUtils.getConnection());
        }
    }
}
