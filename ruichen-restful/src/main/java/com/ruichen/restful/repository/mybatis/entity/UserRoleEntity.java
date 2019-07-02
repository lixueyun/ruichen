package com.ruichen.restful.repository.mybatis.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruichen.restful.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户角色关系表
 * </p>
 *
 * @author lxy
 * @since 2019-07-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_user_role")
public class UserRoleEntity extends BaseEntity<UserRoleEntity> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    @TableField("USER_ID")
    private Long userId;

    /**
     * 角色id
     */
    @TableField("ROLE_ID")
    private Long roleId;

    /**
     * 乐观锁
     */
    @TableField("VERSION")
    private Integer version;

}
