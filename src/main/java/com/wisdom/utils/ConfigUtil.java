package com.wisdom.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author BaldKillerKK
 * @explain: 读取配置文件
 * @return
 * @creed: Talk is cheap,show me the code
 * @date 2019/10/10 9:40
 */
public class ConfigUtil {
    private static Properties prop = new Properties();

    static {
        try {
            InputStream in = ConfigUtil.class.getClassLoader().getResourceAsStream("kerberos.properties");
            prop.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取字符串类型的配置项
     *
     * @param key
     * @return
     */
    public static String getProperty(String key) {
        return prop.getProperty(key);
    }

    /**
     * 获取整数类型的配置项
     *
     * @param key
     * @return java.lang.Integer
     */
    public static Integer getInteger(String key) {
        String value = prop.getProperty(key);
        try {
            return Integer.valueOf(value);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    /**
     * 获取布尔类型的配置项
     *
     * @param key
     * @return java.lang.Boolean
     */
    public static Boolean getBoolean(String key) {
        String value = prop.getProperty(key);
        try {
            return Boolean.valueOf(value);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * 获取Long类型的配置项
     *
     * @param key
     * @return java.lang.Long
     */
    public static Long getLong(String key) {
        String value = prop.getProperty(key);
        try {
            return Long.valueOf(value);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0L;
    }

    public static String[] getStringArray(String key, String separater){
        String value = prop.getProperty(key);
        return value.split(separater);
    }
}
