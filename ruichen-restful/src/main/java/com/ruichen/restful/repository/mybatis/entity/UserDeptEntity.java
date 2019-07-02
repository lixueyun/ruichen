package com.ruichen.restful.repository.mybatis.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruichen.restful.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @ClassName  UserDeptEntity
 * @Description 用户部门关系表
 * @author  lixueyun
 * @Date  2019/7/2 14:33
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_user_dept")
public class UserDeptEntity extends BaseEntity<UserDeptEntity> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    @TableField("USER_ID")
    private Long userId;

    /**
     * 部门id
     */
    @TableField("DEPT_ID")
    private Long deptId;

    /**
     * 乐观锁
     */
    @TableField("VERSION")
    private Integer version;

}
