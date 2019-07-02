package com.ruichen.restful.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruichen.restful.repository.mybatis.entity.DeptEntity;
import com.ruichen.restful.repository.mybatis.mapper.IDeptMapper;
import com.ruichen.restful.service.IDeptService;
import org.springframework.stereotype.Service;

/**
 * @ClassName  DeptServiceImpl
 * @Description 部门表 服务实现类
 * @author  lixueyun
 * @Date  2019/7/2 14:49
 */
@Service
public class DeptServiceImpl extends ServiceImpl<IDeptMapper, DeptEntity> implements IDeptService {

}
