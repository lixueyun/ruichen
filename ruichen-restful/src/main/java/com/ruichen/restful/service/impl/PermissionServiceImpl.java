package com.ruichen.restful.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruichen.restful.repository.mybatis.entity.PermissionEntity;
import com.ruichen.restful.repository.mybatis.mapper.IPermissionMapper;
import com.ruichen.restful.service.IPermissionService;
import org.springframework.stereotype.Service;

/**
 * @ClassName  PermissionServiceImpl
 * @Description 资源表 服务实现类
 * @author  lixueyun
 * @Date  2019/7/2 14:51
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<IPermissionMapper, PermissionEntity> implements IPermissionService {

}
