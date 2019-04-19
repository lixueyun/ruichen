package com.ruichen.restful.common.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * @ClassName  BaseVo
 * @Description
 * @Date  2019/4/4 17:12
 * @author  lixueyun
 */
@Data
@Builder
public class BaseVo<T> {

    @ApiModelProperty(value = "成功标识")
    private Boolean status;

    @ApiModelProperty(value = "返回状态码")
    private String code;

    @ApiModelProperty(value = "信息")
    private String message;

    @ApiModelProperty(value = "返回数据")
    private T data;
}
