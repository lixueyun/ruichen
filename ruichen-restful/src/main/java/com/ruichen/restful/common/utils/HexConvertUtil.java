package com.ruichen.restful.common.utils;

/**
 * @ClassName  HexConvertUtil
 * @Description 进制转换工具
 * @author  lixueyun
 * @Date  2019/7/2 16:33
 */
public class HexConvertUtil {

    /**
     * 1
     */
    private static final Integer INTEGER_1 = 1;

    /**
     * 2
     */
    private static final Integer INTEGER_2 = 2;

    /**
     * @methodName  parseByte2HexStr
     * @description 将二进制转换成16进制
     * @param bytes
     * @author  lixueyun
     * @Date  2019/7/2 16:33
     * @return  java.lang.String
     */
    public static String parseByte2HexStr(byte[] bytes) {
        StringBuffer sb = new StringBuffer();
        for (byte buff : bytes) {
            String hex = Integer.toHexString(buff & 0xFF);
            if (hex.length() == INTEGER_1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * @methodName  parseHexStr2Byte
     * @description 将16进制转换为二进制
     * @param hexStr
     * @author  lixueyun
     * @Date  2019/7/2 16:33
     * @return  byte[]
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < INTEGER_1) {
            return null;
        }
        byte[] result = new byte[hexStr.length() / INTEGER_2];
        for (int i = 0, len = hexStr.length() / INTEGER_2; i < len; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }
}
