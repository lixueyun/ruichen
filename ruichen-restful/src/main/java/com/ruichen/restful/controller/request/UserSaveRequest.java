package com.ruichen.restful.controller.request;

import com.ruichen.restful.common.enums.UserSexEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @ClassName  UserSaveRequest
 * @Description
 * @Date  2019/7/2 16:15
 * @author  lixueyun
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UserSaveRequest implements Serializable {

    private static final long serialVersionUID = -2211228327200632213L;
    /**
     * 头像
     */
    @ApiModelProperty(value="头像")
    private String avatar;

    /**
     * 账号
     */
    @ApiModelProperty(value="账号")
    private String account;

    /**
     * 密码
     */
    @ApiModelProperty(value="密码")
    @Length(max = 8, message = "密码最长不能超过8位!")
    private String password;

    /**
     * 名字
     */
    @ApiModelProperty(value="名字")
    private String name;

    /**
     * 生日
     */
    @ApiModelProperty(value="生日")
    private LocalDateTime birthday;

    /**
     * 性别
     */
    @ApiModelProperty(value="性别")
    private UserSexEnum sex;

    /**
     * 电子邮件
     */
    @ApiModelProperty(value="电子邮件")
    private String email;

    /**
     * 电话
     */
    @ApiModelProperty(value="电话")
    private Integer phone;

}
