package com.ruichen.restful.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruichen.restful.repository.mybatis.entity.MenuEntity;
import com.ruichen.restful.repository.mybatis.mapper.IMenuMapper;
import com.ruichen.restful.service.IMenuService;
import org.springframework.stereotype.Service;

/**
 * @ClassName  MenuServiceImpl
 * @Description 菜单表 服务实现类
 * @author  lixueyun
 * @Date  2019/7/2 14:50
 */
@Service
public class MenuServiceImpl extends ServiceImpl<IMenuMapper, MenuEntity> implements IMenuService {

}
