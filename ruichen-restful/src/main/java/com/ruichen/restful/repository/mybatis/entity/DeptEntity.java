package com.ruichen.restful.repository.mybatis.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruichen.restful.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @ClassName  DeptEntity
 * @Description 部门表
 * @author  lixueyun
 * @Date  2019/7/2 14:27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_dept")
public class DeptEntity extends BaseEntity<DeptEntity> {

    private static final long serialVersionUID = 1L;

    /**
     * 父id
     */
    @TableField("PID")
    private Long pid;

    /**
     * 简称
     */
    @TableField("SIMPLE_NAME")
    private String simpleName;

    /**
     * 全称
     */
    @TableField("FULL_NAME")
    private String fullName;

    /**
     * 排序
     */
    @TableField("SORT")
    private Long sort;

    /**
     * 描述
     */
    @TableField("DESCRIPTION")
    private String description;

    /**
     * 乐观锁
     */
    @TableField("VERSION")
    private Integer version;

}
