package com.ruichen.restful.repository.mybatis.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruichen.restful.common.entity.BaseEntity;
import com.ruichen.restful.common.enums.UserSexEnum;
import com.ruichen.restful.common.enums.UserStatusEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @ClassName  UserEntity
 * @Description  用户表
 * @Date  2019/4/20 19:06
 * @author  lixueyun
 * @version  V1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_user")
public class UserEntity extends BaseEntity<UserEntity> {

    private static final long serialVersionUID = 1L;

    /**
     * 头像
     */
    @TableField("AVATAR")
    private String avatar;

    /**
     * 账号
     */
    @TableField("ACCOUNT")
    private String account;

    /**
     * 密码
     */
    @TableField("PASSWORD")
    private String password;

    /**
     * md5密码盐
     */
    @TableField("SALT")
    private String salt;

    /**
     * 名字
     */
    @TableField("NAME")
    private String name;

    /**
     * 生日
     */
    @TableField("BIRTHDAY")
    private LocalDateTime birthday;

    /**
     * 性别
     */
    @TableField("SEX")
    private UserSexEnum sex;

    /**
     * 电子邮件
     */
    @TableField("EMAIL")
    private String email;

    /**
     * 电话
     */
    @TableField("PHONE")
    private Integer phone;

    /**
     * 状态
     */
    @TableField("STATUS")
    private UserStatusEnum status;

}
