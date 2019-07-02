package com.ruichen.restful.repository.mybatis.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruichen.restful.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @ClassName  RolePermissionEntity
 * @Description 角色资源关系表
 * @author  lixueyun
 * @Date  2019/7/2 14:32
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_role_permission")
public class RolePermissionEntity extends BaseEntity<RolePermissionEntity> {

    private static final long serialVersionUID = 1L;

    /**
     * 角色id
     */
    @TableField("ROLE_ID")
    private Long roleId;

    /**
     * 资源id
     */
    @TableField("PERMISSION_ID")
    private Long permissionId;

    /**
     * 乐观锁
     */
    @TableField("VERSION")
    private Integer version;

}
