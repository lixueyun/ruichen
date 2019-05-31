package com.ruichen.restful.shiro;

import org.springframework.util.Base64Utils;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/**
 * @ClassName  Base64Test
 * @Description  shiro AES key生成器
 * @Date  2019/5/30 20:11
 * @author  lixueyun
 * @version  V1.0
 */
public class Base64Test {

    /**
     * @methodName  main
     * @description  Shiro 记住密码采用的是AES加密，AES key length 需要是16位，该方法生成16位的key
     * @param args
     * @author  lixueyun
     * @Date  2019/5/30 20:12
     * @return  void
     */
    public static void main(String[] args) {
        String keyStr = "ruichen";
        byte[] keys;
        try {
            keys = keyStr.getBytes("UTF-8");
            System.out.println(Base64Utils.encodeToString(Arrays.copyOf(keys, 64)));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

}
