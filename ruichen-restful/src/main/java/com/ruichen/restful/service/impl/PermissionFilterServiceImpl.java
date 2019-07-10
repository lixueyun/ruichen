package com.ruichen.restful.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruichen.restful.repository.mybatis.entity.PermissionFilterEntity;
import com.ruichen.restful.repository.mybatis.mapper.IPermissionFilterMapper;
import com.ruichen.restful.service.IPermissionFilterService;
import org.springframework.stereotype.Service;

/**
 * @ClassName  PermissionFilterServiceImpl
 * @Description 资源过滤表 服务实现类
 * @author  lixueyun
 * @Date  2019/7/2 14:51
 */
@Service
public class PermissionFilterServiceImpl extends ServiceImpl<IPermissionFilterMapper, PermissionFilterEntity> implements IPermissionFilterService {

}
