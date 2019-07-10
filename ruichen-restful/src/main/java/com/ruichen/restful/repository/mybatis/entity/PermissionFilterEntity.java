package com.ruichen.restful.repository.mybatis.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruichen.restful.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @ClassName  PermissionFilterEntity
 * @Description 资源过滤表
 * @author  lixueyun
 * @Date  2019/7/2 14:30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_permission_filter")
public class PermissionFilterEntity extends BaseEntity<PermissionFilterEntity> {

    private static final long serialVersionUID = 1L;

    /**
     * 资源编码
     */
    @TableField("CODE")
    private String code;

    /**
     * 资源名称
     */
    @TableField("NAME")
    private String name;

    /**
     * 资源URL
     */
    @TableField("URL")
    private String url;

    /**
     * 乐观锁
     */
    @TableField("VERSION")
    private Integer version;

}
