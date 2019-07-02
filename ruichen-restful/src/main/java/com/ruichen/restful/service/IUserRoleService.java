package com.ruichen.restful.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruichen.restful.repository.mybatis.entity.UserRoleEntity;

import java.util.List;

/**
 * @ClassName  IUserRoleService
 * @Description 用户角色关系表 服务类
 * @author  lixueyun
 * @Date  2019/7/2 14:43
 */
public interface IUserRoleService extends IService<UserRoleEntity> {


    /**
     * @methodName  getRolesByUserId
     * @description 根据用户id获取角色id集合
     * @param userId
     * @author  lixueyun
     * @Date  2019/7/2 15:00
     * @return  java.util.List<com.ruichen.restful.repository.mybatis.entity.UserRoleEntity>
     */
    List<Long> getRolesByUserId(Long userId);

}
