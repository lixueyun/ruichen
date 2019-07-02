package com.ruichen.restful.common.exception;

import com.ruichen.restful.common.enums.ErrorCodeEnum;
import com.ruichen.restful.common.response.BaseResponse;
import com.ruichen.restful.common.response.BaseResponseBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


/**
 * @ClassName  RestExceptionHandler
 * @Description
 * @Date  2018/8/27 19:15
 * @author  lixueyun
 * @version  V1.0
 */
@ControllerAdvice
@Slf4j
public class RestExceptionHandler {

    @ExceptionHandler(ShiroSpecialException.class)
    @ResponseBody
    public BaseResponse handleException(ShiroSpecialException ex) {
        log.error("ShiroSpecialException: \n    errorCode:{} \n    errorMessage:{}", ex.getBaseEnum().getValue(), ex.getMessage());
        return new BaseResponseBuilder<>().fail(ex.getBaseEnum().getValue(), ex.getMessage()).build();
    }

    @ExceptionHandler(ControllerException.class)
    @ResponseBody
    public BaseResponse handleException(ControllerException ex) {
        log.error("ControllerException: \n    errorCode:{} \n    errorMessage:{}", ex.getBaseEnum().getValue(), ex.getMessage());
        return new BaseResponseBuilder<>().fail(ex.getBaseEnum().getValue(), ex.getMessage()).build();
    }

    @ExceptionHandler(BindException.class)
    @ResponseBody
    public BaseResponse handleException(BindException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        StringBuffer message = bindingResult(bindingResult);
        log.error("BindException: \n    errorCode:{} \n    errorMessage:{}", ErrorCodeEnum.E101009.getValue(), ex.getMessage());
        return new BaseResponseBuilder().fail(ErrorCodeEnum.E101009.getValue(), ErrorCodeEnum.E101009.getText() + " " +  message.substring(0, message.length() - 1)).build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public BaseResponse handleException(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        StringBuffer message = bindingResult(bindingResult);
        log.error("MethodArgumentNotValidException: \n    errorCode:{} \n    errorMessage:{}", ErrorCodeEnum.E101009.getValue(), ex.getMessage());
        return new BaseResponseBuilder().fail(ErrorCodeEnum.E101009.getValue(), ErrorCodeEnum.E101009.getText() + " " +  message.substring(0, message.length() - 1)).build();
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public BaseResponse handleException(Exception ex) {
        log.error("Exception: \n    errorCode:{} \n    errorMessage:{}", ErrorCodeEnum.E101000.getValue(), ex.getMessage());
        return new BaseResponseBuilder().fail(ErrorCodeEnum.E101000.getValue(), ErrorCodeEnum.E101000.getText()).build();
    }

    private StringBuffer bindingResult(BindingResult bindingResult) {
        List<FieldError> fes = bindingResult.getFieldErrors();
        StringBuffer message = new StringBuffer();
        for (FieldError fieldError : fes) {
            message.append(" 字段:");
            message.append(fieldError.getField());
            message.append(" 验证错误:");
            message.append(fieldError.getDefaultMessage());
            message.append(",");
        }
        return message;
    }

}
