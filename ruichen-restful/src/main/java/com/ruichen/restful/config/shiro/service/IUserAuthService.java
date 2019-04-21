package com.ruichen.restful.config.shiro.service;

import com.ruichen.restful.config.shiro.ShiroUser;
import com.ruichen.restful.repository.mybatis.entity.UserEntity;
import org.apache.shiro.authc.SimpleAuthenticationInfo;

/**
 * @ClassName  IUserAuthService
 * @Description  用户权限service
 * @Date  2019/4/20 21:15
 * @author  lixueyun
 * @version  V1.0
 */
public interface IUserAuthService {

    /**
     * @methodName  user
     * @description  账号校验
     * @param account
     * @author  lixueyun
     * @Date  2019/4/20 21:12
     * @return  com.ruichen.restful.repository.mybatis.entity.UserEntity
     */
    UserEntity user(String account);

    /**
     * @methodName  shiroUser
     * @description  装换shiroUser
     * @param user
     * @author  lixueyun
     * @Date  2019/4/20 21:16
     * @return  com.ruichen.restful.config.shiro.ShiroUser
     */
    ShiroUser shiroUser(UserEntity user);

    /**
     * @methodName  info
     * @description  处理认证
     * @param shiroUser
     * @param user
     * @param realmName
     * @author  lixueyun
     * @Date  2019/4/20 21:19
     * @return  org.apache.shiro.authc.SimpleAuthenticationInfo
     */
    SimpleAuthenticationInfo info(ShiroUser shiroUser, UserEntity user, String realmName);
}
