package com.ruichen.restful.config.shiro;

import com.ruichen.restful.common.constant.ShiroConstant;
import com.ruichen.restful.common.enums.ErrorCodeEnum;
import com.ruichen.restful.common.enums.UserStatusEnum;
import com.ruichen.restful.common.utils.JwtUtil;
import com.ruichen.restful.config.shiro.jwt.JwtToken;
import com.ruichen.restful.repository.mybatis.entity.PermissionEntity;
import com.ruichen.restful.repository.mybatis.entity.RoleEntity;
import com.ruichen.restful.repository.mybatis.entity.UserEntity;
import com.ruichen.restful.service.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @ClassName  自定义Realm
 * @Description
 * @author  lixueyun
 * @Date  2019/7/2 10:41
 */
@Slf4j
@Component
public class UserRealm extends AuthorizingRealm {

	@Autowired
	private IUserService userService;

	@Autowired
    private RedisTemplate redisTemplate;

	@Autowired
    private IUserRoleService userRoleService;

	@Autowired
    private IRoleService roleService;

	@Autowired
    private IRolePermissionService rolePermissionService;

	@Autowired
    private IPermissionService permissionService;

    /**
     * 大坑，必须重写此方法，不然Shiro会报错
     */
    @Override
    public boolean supports(AuthenticationToken authenticationToken) {
        return authenticationToken instanceof JwtToken;
    }

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
        String token = (String) authcToken.getCredentials();
        // 解密获得account，用于和数据库进行对比
        String account = JwtUtil.getClaim(token, ShiroConstant.ACCOUNT);
        // 帐号为空
        if (StringUtils.isEmpty(account)) {
            throw new AuthenticationException(ErrorCodeEnum.E101004.getText());
        }
        //获取用户信息
        UserEntity user = userService.getUserByAccount(account);
        // 账号不存在
        if (user == null) {
            throw new AuthenticationException(ErrorCodeEnum.E101001.getText());
        }
        // 账号被冻结
        if (!user.getStatus().equals(UserStatusEnum.ENABLE)) {
            throw new AuthenticationException(ErrorCodeEnum.E101002.getText());
        }
        // 开始认证，要AccessToken认证通过，且Redis中存在RefreshToken，且两个Token时间戳一致
        if (JwtUtil.verify(token) && redisTemplate.hasKey(ShiroConstant.PREFIX_SHIRO_REFRESH_TOKEN + account)) {
            // 获取RefreshToken的时间戳
            String currentTimeMillisRedis = redisTemplate.opsForValue().get(ShiroConstant.PREFIX_SHIRO_REFRESH_TOKEN + account).toString();
            // 获取AccessToken时间戳，与RefreshToken的时间戳对比
            if (JwtUtil.getClaim(token, ShiroConstant.CURRENT_TIME_MILLIS).equals(currentTimeMillisRedis)) {
                return new SimpleAuthenticationInfo(token, token, "userRealm");
            }
        }
        throw new AuthenticationException(ErrorCodeEnum.E101004.getText());
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
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        String account = JwtUtil.getClaim(principals.toString(), ShiroConstant.ACCOUNT);

        //获取用户信息
        UserEntity userEntity = userService.getUserByAccount(account);
        //获取用户角色id集合
        List<Long> rolesIds = userRoleService.getRolesByUserId(userEntity.getId());

        //角色为空直接返回
        if (CollectionUtils.isEmpty(rolesIds)) {
            return simpleAuthorizationInfo;
        }

        //获取角色详细信息
        List<RoleEntity> roleEntities = (List<RoleEntity>) roleService.listByIds(rolesIds);

        //获取资源id集合
        List<Long> permissionIds = rolePermissionService.getPermissionsByRoleIds(rolesIds);

        //资源为空直接返回
        if (CollectionUtils.isEmpty(permissionIds)) {
            return simpleAuthorizationInfo;
        }

        //获取资源详情
        List<PermissionEntity> permissionEntities = (List<PermissionEntity>) permissionService.listByIds(permissionIds);

        //角色集合
        Set<String> roleNameSet = new HashSet<>();
        //资源集合
        Set<String> permissionSet = new HashSet<>();
        //获得角色名称集合并赋值
        List<String> roleNameList = roleEntities.stream().map(RoleEntity::getName)
                .collect(Collectors.toList());
        roleNameSet.addAll(roleNameList);

        //获得资源url集合
        List<String> permissionUrlList = permissionEntities.stream().map(PermissionEntity::getUrl)
                .collect(Collectors.toList());
        permissionSet.addAll(permissionUrlList);
        //添加权限
        simpleAuthorizationInfo.addStringPermissions(permissionSet);
        simpleAuthorizationInfo.addRoles(roleNameSet);
        return simpleAuthorizationInfo;
	}
}
