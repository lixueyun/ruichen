package com.ruichen.restful.common.enums;
/**
 * @ClassName  UserSexEnum
 * @Description  用户性别枚举
 * @Date  2019/4/20 20:57
 * @author  lixueyun
 * @version  V1.0
 */
public enum UserSexEnum implements IBaseEnum{

    MAN(0, "男"),
    WOMEN(1, "女");

    private Integer value;

    private String text;

    /**
     * @param value
     * @param text
     */
    UserSexEnum(final Integer value, final String text) {
        this.value = value;
        this.text = text;
    }

    @Override
    public Integer getValue() {
        return this.value;
    }

    @Override
    public String getText() {
        return this.text;
    }
}
