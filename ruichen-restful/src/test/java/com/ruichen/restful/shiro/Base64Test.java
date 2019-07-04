package com.ruichen.restful.shiro;

import com.ruichen.restful.common.utils.Base64ConvertUtil;
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
        String keyStr = "LiRuiChen&MaJingCun";
        String keyStr1 = "LiXueYun&MaJingCun";
        byte[] keys;
        try {
            keys = keyStr.getBytes("UTF-8");
            System.out.println(Base64Utils.encodeToString(Arrays.copyOf(keys, 64)));

            String decode = Base64ConvertUtil.decode("U0JBUElKV1RkV2FuZzkyNjQ1NA==");
            System.out.println(decode);
            String encode = Base64ConvertUtil.encode(keyStr);
            System.out.println(encode);
            String encode1 = Base64ConvertUtil.encode(keyStr1);
            System.out.println(encode1);
            String encode2 = Base64ConvertUtil.decode(encode);
            System.out.println(encode2);
            String encode3 = Base64ConvertUtil.decode(encode1);
            System.out.println(encode3);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

}
