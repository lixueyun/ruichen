package com.ruichen.restful.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruichen.restful.repository.mybatis.entity.UserRoleEntity;
import com.ruichen.restful.repository.mybatis.mapper.IUserRoleMapper;
import com.ruichen.restful.service.IUserRoleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName  UserRoleServiceImpl
 * @Description 用户角色关系表 服务实现类
 * @author  lixueyun
 * @Date  2019/7/2 14:57
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<IUserRoleMapper, UserRoleEntity> implements IUserRoleService {

    /**
     * @methodName  getRolesByUserId
     * @description 根据用户id获取角色id集合
     * @param userId
     * @author  lixueyun
     * @Date  2019/7/2 15:00
     * @return  java.util.List<com.ruichen.restful.repository.mybatis.entity.UserRoleEntity>
     */
    @Override
    public List<Long> getRolesByUserId(Long userId) {
        QueryWrapper<UserRoleEntity> wrapper = new QueryWrapper<>();
        wrapper.select("ROLE_ID");
        wrapper.eq("USER_ID", userId);
        List<UserRoleEntity> list = this.list(wrapper);
        return list.stream().map(UserRoleEntity::getRoleId)
                .collect(Collectors.toList());
    }
}
