package com.ruichen.restful.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruichen.restful.repository.mybatis.entity.UserEntity;
import com.ruichen.restful.repository.mybatis.mapper.IUserMapper;
import com.ruichen.restful.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * @ClassName  LoginServiceImpl
 * @Description  用户service
 * @Date  2019/4/20 12:15
 * @author  lixueyun
 * @version  V1.0
 */
@Service
public class UserServiceImpl extends ServiceImpl<IUserMapper, UserEntity> implements IUserService {

    @Override
    public UserEntity getUserByAccount(String account) {
        QueryWrapper<UserEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("ACCOUNT", account);
        UserEntity userEntity = this.getOne(wrapper);
        return userEntity;
    }
}
