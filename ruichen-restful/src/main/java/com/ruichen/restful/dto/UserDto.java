package com.ruichen.restful.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @ClassName  UserDto
 * @Description  用户返回实体
 * @Date  2019/4/20 20:47
 * @author  lixueyun
 * @version  V1.0
 */
@Data
public class UserDto {

    /**
     * 头像
     */
    private String avatar;

    /**
     * 账号
     */
    private String account;

    /**
     * 密码
     */
    private String password;

    /**
     * md5密码盐
     */
    private String salt;

    /**
     * 名字
     */
    private String name;

    /**
     * 生日
     */
    private LocalDateTime birthday;

    /**
     * 性别
     */
    private Integer sex;

    /**
     * 电子邮件
     */
    private String email;

    /**
     * 电话
     */
    @TableField("PHONE")
    private Integer phone;

    /**
     * 状态
     */
    private Integer status;
}
