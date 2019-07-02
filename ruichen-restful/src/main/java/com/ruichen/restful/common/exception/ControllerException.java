package com.ruichen.restful.common.exception;

import com.ruichen.restful.common.enums.IBaseEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @ClassName  ControllerException
 * @Description  自定义controller异常
 * @Date  2019/7/1 22:01
 * @author  lixueyun
 * @version  V1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ControllerException extends RuntimeException{

    private IBaseEnum baseEnum;

    public ControllerException(IBaseEnum baseEnum , String msg){
        super(msg);
        this.baseEnum = baseEnum;
    }

    public ControllerException() {
        super();
    }
}
