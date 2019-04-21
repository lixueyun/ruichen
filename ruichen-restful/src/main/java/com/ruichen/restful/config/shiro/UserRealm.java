package com.ruichen.restful.config.shiro;

import com.ruichen.restful.common.constants.ShiroConstants;
import com.ruichen.restful.config.shiro.service.IUserAuthService;
import com.ruichen.restful.repository.mybatis.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author: hxy
 * @description: 自定义Realm
 * @date: 2017/10/24 10:06
 */
@Slf4j
@Component
public class UserRealm extends AuthorizingRealm {

	@Autowired
	private IUserAuthService userAuthService;

    /**
     * @methodName  doGetAuthenticationInfo
     * @description  登录逻辑  LoginController.login()方法中执行Subject.login()时 执行此方法
     * @param authcToken
     * @author  lixueyun
     * @Date  2019/4/20 12:37
     * @return  org.apache.shiro.authc.AuthenticationInfo
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        UserEntity user = userAuthService.user(token.getUsername());
        ShiroUser shiroUser = userAuthService.shiroUser(user);
        return userAuthService.info(shiroUser, user, super.getName());
    }

    /**
     * @methodName  doGetAuthorizationInfo
     * @description  授权逻辑
     * @param principals
     * @author  lixueyun
     * @Date  2019/4/20 12:36
     * @return  org.apache.shiro.authz.AuthorizationInfo
     */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();
        List<Long> roleList = shiroUser.getRoleList();
        Set<String> permissionSet = new HashSet<>();
        Set<String> roleNameSet = new HashSet<>();
        //TODO 批量获取菜单url
        //TODO 批量获取角色name
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addStringPermissions(permissionSet);
        simpleAuthorizationInfo.addRoles(roleNameSet);
        return simpleAuthorizationInfo;
	}

    /**
     * @methodName  setCredentialsMatcher
     * @description  设置认证加密方式
     * @param credentialsMatcher
     * @author  lixueyun
     * @Date  2019/4/20 14:24
     * @return  void
     */
    @Override
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
        HashedCredentialsMatcher md5CredentialsMatcher = new HashedCredentialsMatcher();
        md5CredentialsMatcher.setHashAlgorithmName(ShiroConstants.HASH_ALGORITHM_NAME);
        md5CredentialsMatcher.setHashIterations(ShiroConstants.HASH_ITERATIONS);
        super.setCredentialsMatcher(md5CredentialsMatcher);
    }
}
