package com.ruichen.restful.common.exception;

import com.ruichen.restful.common.enums.IBaseEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @ClassName  ShiroSpecialException
 * @Description  自定义shiro异常
 * @Date  2019/7/1 22:01
 * @author  lixueyun
 * @version  V1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ShiroSpecialException extends RuntimeException{

    private IBaseEnum baseEnum;

    public ShiroSpecialException(IBaseEnum baseEnum , String msg){
        super(msg);
        this.baseEnum = baseEnum;
    }

    public ShiroSpecialException() {
        super();
    }
}
