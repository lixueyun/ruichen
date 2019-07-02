package com.ruichen.restful.common.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @ClassName  BaseVo
 * @Description
 * @Date  2019/4/4 17:12
 * @author  lixueyun
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BaseResponse<T> {

    @ApiModelProperty(value = "成功标识")
    private Boolean status;

    @ApiModelProperty(value = "返回状态码")
    private String code;

    @ApiModelProperty(value = "信息")
    private String message;

    @ApiModelProperty(value = "返回数据")
    private T data;

}
