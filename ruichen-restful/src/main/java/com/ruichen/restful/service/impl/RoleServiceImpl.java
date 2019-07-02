package com.ruichen.restful.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruichen.restful.repository.mybatis.entity.RoleEntity;
import com.ruichen.restful.repository.mybatis.mapper.IRoleMapper;
import com.ruichen.restful.service.IRoleService;
import org.springframework.stereotype.Service;

/**
 * @ClassName  RoleServiceImpl
 * @Description 角色表 服务实现类
 * @author  lixueyun
 * @Date  2019/7/2 14:54
 */
@Service
public class RoleServiceImpl extends ServiceImpl<IRoleMapper, RoleEntity> implements IRoleService {

}
