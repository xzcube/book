package utils;

import org.apache.commons.beanutils.BeanUtils;

import java.util.Map;

/**
 * 把Map中的值注入到对应的javaBean属性中，降低代码的耦合度
 * @author xzcube
 * @date 2021/1/25 21:22
 */
public class WebUtils {
    public static <T> T copyParamBean(Map value, T bean){
        try {
            BeanUtils.populate(bean, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }

    /**
     * 将字符串转化为int类型的数据
     * @param stringInt
     * @return
     */
    public static int parseInt(String stringInt){
        try {
            return Integer.parseInt(stringInt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static int parseInt(String stringInt, Integer defaultValue){
        try {
            return Integer.parseInt(stringInt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultValue;
    }
}
