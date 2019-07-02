package com.ruichen.restful.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruichen.restful.repository.mybatis.entity.RolePermissionEntity;
import com.ruichen.restful.repository.mybatis.mapper.IRolePermissionMapper;
import com.ruichen.restful.service.IRolePermissionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName  RolePermissionServiceImpl
 * @Description 角色资源关系表 服务实现类
 * @author  lixueyun
 * @Date  2019/7/2 14:54
 */
@Service
public class RolePermissionServiceImpl extends ServiceImpl<IRolePermissionMapper, RolePermissionEntity> implements IRolePermissionService {

    /**
     * @methodName  getPermissionsByRoleIds
     * @description 根据角色id获取资源集合
     * @param roleIds
     * @author  lixueyun
     * @Date  2019/7/2 15:10
     * @return  java.util.List<java.lang.String>
     */
    @Override
    public List<Long> getPermissionsByRoleIds(List<Long> roleIds) {
        QueryWrapper<RolePermissionEntity> wrapper = new QueryWrapper<>();
        wrapper.select("PERMISSION_ID");
        wrapper.in("ROLE_ID");
        List<RolePermissionEntity> list = this.list(wrapper);
        return list.stream().map(RolePermissionEntity::getPermissionId)
                .collect(Collectors.toList());
    }
}
