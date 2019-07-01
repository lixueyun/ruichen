package com.ruichen.restful.common.exception;
/**
 * @ClassName  ShiroSpecialException
 * @Description  自定义shiro异常
 * @Date  2019/7/1 22:01
 * @author  lixueyun
 * @version  V1.0
 */
public class ShiroSpecialException extends RuntimeException{

    public ShiroSpecialException(String msg){
        super(msg);
    }

    public ShiroSpecialException() {
        super();
    }
}
