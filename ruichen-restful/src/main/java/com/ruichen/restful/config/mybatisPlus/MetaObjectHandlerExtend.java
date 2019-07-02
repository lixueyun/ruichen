package com.ruichen.restful.config.mybatisPlus;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @ClassName  MetaObjectHandlerExtend
 * @Description mybatis plus 新增和修改配置
 * @Date  2019/4/20 19:00
 * @author  lixueyun
 * @version  V1.0
 */
@Component
public class MetaObjectHandlerExtend implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        metaObject.setValue("createTime", LocalDateTime.now());
        metaObject.setValue("createUser", "admin");
        metaObject.setValue("updateTime", LocalDateTime.now());
        metaObject.setValue("updateUser", "admin");
        metaObject.setValue("delFlag", 0);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
        setFieldValByName("updateUser","admin", metaObject);
    }
}
