package com.ruichen.restful.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruichen.restful.common.service.BaseService;
import com.ruichen.restful.repository.mybatis.entity.UserEntity;
import com.ruichen.restful.repository.mybatis.mapper.IUserMapper;
import com.ruichen.restful.service.IUserService;

/**
 * @ClassName  LoginServiceImpl
 * @Description  用户service
 * @Date  2019/4/20 12:15
 * @author  lixueyun
 * @version  V1.0
 */
public class UserServiceImpl extends BaseService<IUserMapper, UserEntity> implements IUserService {

    @Override
    public UserEntity getUserByAccount(String account) {
        QueryWrapper<UserEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("AVATAR", account);
        UserEntity userEntity = this.getOne(wrapper);
        return userEntity;
    }
}
