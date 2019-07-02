package com.ruichen.restful.repository.mybatis.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruichen.restful.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @ClassName  MenuEntity
 * @Description 菜单表
 * @author  lixueyun
 * @Date  2019/7/2 14:29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_menu")
public class MenuEntity extends BaseEntity<MenuEntity> {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单编号
     */
    @TableField("CODE")
    private Long code;

    /**
     * 菜单父编号
     */
    @TableField("P_CODE")
    private Long pCode;

    /**
     * 菜单名称
     */
    @TableField("NAME")
    private String name;

    /**
     * 菜单层级
     */
    @TableField("LEVELS")
    private Integer levels;

    /**
     * 菜单排序号
     */
    @TableField("SORT")
    private Long sort;

    /**
     * 菜单图标
     */
    @TableField("ICON")
    private String icon;

    /**
     * url地址
     */
    @TableField("URL")
    private String url;

    /**
     * 备注
     */
    @TableField("DESCRIPTION")
    private String description;

    /**
     * 状态
     */
    @TableField("STATUS")
    private Integer status;

    /**
     * 乐观锁
     */
    @TableField("VERSION")
    private Integer version;

}
