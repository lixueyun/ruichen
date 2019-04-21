package com.ruichen.restful.common.service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName  BaseService
 * @Description  BaseService
 * @Date  2019/4/20 12:16
 * @author  lixueyun
 * @version  V1.0
 */
@Service
@Transactional
public class BaseService<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> {
}
