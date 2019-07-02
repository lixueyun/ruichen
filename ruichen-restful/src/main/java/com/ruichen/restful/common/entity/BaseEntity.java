package com.ruichen.restful.common.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @ClassName  BaseEntity
 * @Description  mybatis plus 基础实体
 * @Date  2019/4/20 19:05
 * @author  lixueyun
 * @version  V1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public abstract class BaseEntity <T extends Model> extends Model<T> {

    @TableId(value = "id")
    private Long id;

    @TableField(value="create_time",fill=FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(value="create_user",fill=FieldFill.INSERT)
    private String createUser;

    @TableField(value="update_time",fill=FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField(value="update_user",fill=FieldFill.INSERT_UPDATE)
    private String updateUser;

    @TableField(value="del_flag",fill=FieldFill.INSERT)
    @TableLogic
    private Integer delFlag;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
