package com.ruichen.restful.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruichen.restful.repository.mybatis.entity.RolePermissionEntity;

import java.util.List;

/**
 * @ClassName  IRolePermissionService
 * @Description 角色资源关系表 服务类
 * @author  lixueyun
 * @Date  2019/7/2 14:40
 */
public interface IRolePermissionService extends IService<RolePermissionEntity> {

    /**
     * @methodName  getPermissionsByRoleIds
     * @description 根据角色id获取资源集合
     * @param roleIds
     * @author  lixueyun
     * @Date  2019/7/2 15:10
     * @return  java.util.List<java.lang.String>
     */
    List<Long> getPermissionsByRoleIds(List<Long> roleIds);
}
