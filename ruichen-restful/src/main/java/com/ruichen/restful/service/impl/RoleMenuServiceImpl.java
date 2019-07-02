package com.ruichen.restful.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruichen.restful.repository.mybatis.entity.RoleMenuEntity;
import com.ruichen.restful.repository.mybatis.mapper.IRoleMenuMapper;
import com.ruichen.restful.service.IRoleMenuService;
import org.springframework.stereotype.Service;

/**
 * @ClassName  RoleMenuServiceImpl
 * @Description 角色菜单关系表 服务实现类
 * @author  lixueyun
 * @Date  2019/7/2 14:52
 */
@Service
public class RoleMenuServiceImpl extends ServiceImpl<IRoleMenuMapper, RoleMenuEntity> implements IRoleMenuService {

}
