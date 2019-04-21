package com.ruichen.restful.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruichen.restful.repository.mybatis.entity.UserEntity;

/**
 * @ClassName  IUserService
 * @Description  用户service
 * @Date  2019/4/20 12:14
 * @author  lixueyun
 * @version  V1.0
 */
public interface IUserService extends IService<UserEntity> {

    /**
     * @methodName  getUserByAccount
     * @description  根据账号查询
     * @param account
     * @author  lixueyun
     * @Date  2019/4/20 20:49
     * @return  com.ruichen.restful.repository.mybatis.entity.UserEntity
     */
    UserEntity getUserByAccount(String account);
}
