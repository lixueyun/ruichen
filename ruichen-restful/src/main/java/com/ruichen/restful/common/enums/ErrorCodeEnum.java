package com.ruichen.restful.common.enums;

/**
 * @ClassName  ErrorCodeEnum
 * @Description  异常错误码
 * @Date  2019/4/20 12:22
 * @author  lixueyun
 * @version  V1.0
 */
public enum  ErrorCodeEnum implements IBaseEnum{


    E101000("101000", "系统错误!"),
    E101001("101001", "登录失败，用户名或密码错误!"),
    E101002("101002", "该账号已被锁定,请联系管理员!"),
    E101003("101003", "无权访问(Unauthorized):请先登录!"),
    E101004("101004", "无权访问(Unauthorized):token无效,请尝试重新登录!"),
    E101005("101005", "无权访问(Unauthorized):该接口无权访问,请联系管理员!"),
    E101009("101009", "参数验证异常!"),
    E101010("101010", "该账号已存在!"),
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
