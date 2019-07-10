package com.ruichen.restful.config.mybatisPlus;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.ruichen.restful.config.RequestContext;
import com.ruichen.restful.repository.mybatis.entity.UserEntity;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.beans.factory.annotation.Autowired;
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


    @Autowired
    private RequestContext requestContext;

    @Override
    public void insertFill(MetaObject metaObject) {
        UserEntity userEntity = requestContext.currentUser();
        String currentUser = "admin";
        if (null != userEntity) {
            currentUser = userEntity.getAccount();
        }
        metaObject.setValue("createTime", LocalDateTime.now());
        metaObject.setValue("createUser", currentUser);
        metaObject.setValue("updateTime", LocalDateTime.now());
        metaObject.setValue("updateUser", currentUser);
        metaObject.setValue("delFlag", 0);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        UserEntity userEntity = requestContext.currentUser();
        String currentUser = "admin";
        if (null != userEntity) {
            currentUser = userEntity.getAccount();
        }
        setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
        setFieldValByName("updateUser",currentUser, metaObject);
    }
}
