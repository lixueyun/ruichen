package com.ruichen.restful.config;

import com.ruichen.restful.common.constant.ShiroConstant;
import com.ruichen.restful.common.utils.JwtUtil;
import com.ruichen.restful.repository.mybatis.entity.UserEntity;
import com.ruichen.restful.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName  RequestContext
 * @Description
 * @Date  2019/7/10 10:34
 * @author  lixueyun
 */
@Slf4j
@Component
public class RequestContext {

    @Autowired
    private IUserService userService;

    private ThreadLocal<UserEntity> userThreadLocal = new ThreadLocal<>();

    /**
     * @methodName  currentUser
     * @description 通过token进行赋值
     * @param authcToken
     * @author  lixueyun
     * @Date  2019/7/10 10:52
     * @return  com.ruichen.restful.repository.mybatis.entity.UserEntity
     */
    public UserEntity currentUser(AuthenticationToken authcToken) {
        String token = (String) authcToken.getCredentials();
        // 解密获得account
        String account = JwtUtil.getClaim(token, ShiroConstant.ACCOUNT);
        //获取用户信息
        UserEntity userEntity = userService.getUserByAccount(account);
        //进行赋值
        userThreadLocal.set(userEntity);
        return userEntity;
    }


    /**
     * @methodName  currentUser
     * @description 获取当前用户信息
     * @param
     * @author  lixueyun
     * @Date  2019/7/10 10:52
     * @return  com.ruichen.restful.repository.mybatis.entity.UserEntity
     */
    public UserEntity currentUser(){
        return userThreadLocal.get();
    }

}
