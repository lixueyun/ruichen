package com.ruichen.restful.config.shiro.service.impl;

import com.ruichen.restful.common.enums.UserStatusEnum;
import com.ruichen.restful.config.shiro.ShiroUser;
import com.ruichen.restful.config.shiro.service.IUserAuthService;
import com.ruichen.restful.repository.mybatis.entity.UserEntity;
import com.ruichen.restful.service.IUserService;
import org.apache.shiro.authc.CredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @ClassName  UserAuthService
 * @Description  用户权限service实现类
 * @Date  2019/4/20 21:09
 * @author  lixueyun
 * @version  V1.0
 */
@Service
public class UserAuthServiceImpl implements IUserAuthService {

    @Autowired
    private IUserService userService;

    /**
     * @methodName  user
     * @description  账号校验
     * @param account
     * @author  lixueyun
     * @Date  2019/4/20 21:12
     * @return  com.ruichen.restful.repository.mybatis.entity.UserEntity
     */
    @Override
    public UserEntity user(String account) {
        UserEntity userEntity = userService.getUserByAccount(account);
        // 账号不存在
        if (Objects.isNull(userEntity)) {
            throw new CredentialsException();
        }
        // 账号被冻结
        if (!userEntity.getStatus().equals(UserStatusEnum.ENABLE)) {
            throw new LockedAccountException();
        }
        return userEntity;
    }

    /**
     * @methodName  shiroUser
     * @description  装换shiroUser
     * @param userEntity
     * @author  lixueyun
     * @Date  2019/4/20 21:17
     * @return  com.ruichen.restful.config.shiro.ShiroUser
     */
    @Override
    public ShiroUser shiroUser(UserEntity userEntity) {
        ShiroUser shiroUser = new ShiroUser();
        shiroUser.setAccount(userEntity.getAccount());
        shiroUser.setName(userEntity.getName());
        shiroUser.setBirthday(userEntity.getBirthday());
        shiroUser.setPhone(userEntity.getPhone());
        shiroUser.setSex(userEntity.getSex());
        shiroUser.setEmail(userEntity.getEmail());
        shiroUser.setAvatar(userEntity.getAvatar());
        return shiroUser;
    }

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
    @Override
    public SimpleAuthenticationInfo info(ShiroUser shiroUser, UserEntity user, String realmName) {
        String credentials = user.getPassword();
        String source = user.getSalt();
        ByteSource credentialsSalt = new Md5Hash(source);
        return new SimpleAuthenticationInfo(shiroUser, credentials, credentialsSalt, realmName);
    }
}
