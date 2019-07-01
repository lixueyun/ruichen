package com.ruichen.restful.common.utils;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * @ClassName  Base64ConvertUtil
 * @Description  Base64工具
 * @Date  2019/7/1 21:55
 * @author  lixueyun
 * @version  V1.0
 */
public class Base64ConvertUtil {

    /**
     * @methodName  encode
     * @description  加密JDK1.8
     * @param str
     * @author  lixueyun
     * @Date  2019/7/1 21:55
     * @return  java.lang.String
     */
    public static String encode(String str) throws UnsupportedEncodingException {
        byte[] encodeBytes = Base64.getEncoder().encode(str.getBytes("utf-8"));
        return new String(encodeBytes);
    }

    /**
     * @methodName  decode
     * @description  解密JDK1.8
     * @param str
     * @author  lixueyun
     * @Date  2019/7/1 21:55
     * @return  java.lang.String
     */
    public static String decode(String str) throws UnsupportedEncodingException {
        byte[] decodeBytes = Base64.getDecoder().decode(str.getBytes("utf-8"));
        return new String(decodeBytes);
    }

}
