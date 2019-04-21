package com.ruichen.restful.common.enums;
/**
 * @ClassName  UserStatusEnum
 * @Description  用户状态，枚举
 * @Date  2019/4/20 20:57
 * @author  lixueyun
 * @version  V1.0
 */
public enum UserStatusEnum implements IBaseEnum{

    ENABLE(0, "启用"),
    LOCKED(1, "冻结");

    private Integer value;

    private String text;

    /**
     * @param value
     * @param text
     */
    UserStatusEnum(final Integer value, final String text) {
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
