package com.ruichen.restful.common.enums;

/**
 * @ClassName  ErrorCodeEnum
 * @Description  TODO
 * @Date  2019/4/20 12:22
 * @author  lixueyun
 * @version  V1.0
 */
public enum  ErrorCodeEnum implements IBaseEnum{


    E101000("101000", "系统错误!"),
    E101001("101001", "登录失败!"),
    E101002("101002", "用户名或密码错误!"),
    E999999("999999", "占位!");


    private String value;

    private String text;

    /**
     * @param value
     * @param text
     */
    ErrorCodeEnum(final String value, final String text) {
        this.value = value;
        this.text = text;
    }

    @Override
    public String getValue() {
        return this.value;
    }

    @Override
    public String getText() {
        return this.text;
    }
}
