package com.ruichen.restful.repository.mybatis.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruichen.restful.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @ClassName  RoleMenuEntity
 * @Description 角色菜单关系表
 * @author  lixueyun
 * @Date  2019/7/2 14:32
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_role_menu")
public class RoleMenuEntity extends BaseEntity<RoleMenuEntity> {

    private static final long serialVersionUID = 1L;

    /**
     * 角色id
     */
    @TableField("ROLE_ID")
    private Long roleId;

    /**
     * 菜单id
     */
    @TableField("MENU_ID")
    private Long menuId;

    /**
     * 乐观锁
     */
    @TableField("VERSION")
    private Integer version;


}
