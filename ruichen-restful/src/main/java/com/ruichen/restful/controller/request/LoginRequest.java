package com.ruichen.restful.controller.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * @ClassName  LoginRequest
 * @Description
 * @Date  2019/7/8 11:02
 * @author  lixueyun
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class LoginRequest implements Serializable {


    private static final long serialVersionUID = 8236583923627357213L;

    /**
     * 账号
     */
    @ApiModelProperty(value="账号")
    @NotEmpty(message = "账号不能为空")
    private String account;

    /**
     * 密码
     */
    @ApiModelProperty(value="密码")
    @NotEmpty(message = "密码不能为空")
    private String password;
}
