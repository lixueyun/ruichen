package com.ruichen.restful.repository.mybatis.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruichen.restful.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @ClassName  RoleEntity
 * @Description 角色表
 * @author  lixueyun
 * @Date  2019/7/2 14:31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_role")
public class RoleEntity extends BaseEntity<RoleEntity> {

    private static final long serialVersionUID = 1L;

    /**
     * 父id
     */
    @TableField("PID")
    private Long pid;

    /**
     * 名字
     */
    @TableField("NAME")
    private String name;

    /**
     * 描述
     */
    @TableField("DESCRIPTION")
    private String description;

    /**
     * 序号
     */
    @TableField("SORT")
    private Long sort;

    /**
     * 乐观锁
     */
    @TableField("VERSION")
    private Integer version;

}
