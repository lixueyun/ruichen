package com.ruichen.restful.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruichen.restful.repository.mybatis.entity.UserDeptEntity;
import com.ruichen.restful.repository.mybatis.mapper.IUserDeptMapper;
import com.ruichen.restful.service.IUserDeptService;
import org.springframework.stereotype.Service;

/**
 * @ClassName  UserDeptServiceImpl
 * @Description 用户部门关系表 服务实现类
 * @author  lixueyun
 * @Date  2019/7/2 14:56
 */
@Service
public class UserDeptServiceImpl extends ServiceImpl<IUserDeptMapper, UserDeptEntity> implements IUserDeptService {

}
