package com.ruichen.restful.config.mybatisPlus;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName  MybatisPlusConfig
 * @Description  MybatisPlus配置类
 * @Date  2019/4/20 19:02
 * @author  lixueyun
 * @version  V1.0
 */
@Configuration
public class MybatisPlusConfig {

    /**
     * @methodName  performanceInterceptor
     * @description  plus 的性能优化
     * @param
     * @author  lixueyun
     * @Date  2019/4/20 19:03
     * @return  com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor
     */
    @Bean
    public PerformanceInterceptor performanceInterceptor() {
        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
        /*<!-- SQL 执行性能分析，开发环境使用，线上不推荐。 maxTime 指的是 sql 最大执行时长 -->*/
//        performanceInterceptor.setMaxTime(1000);
        /*<!--SQL是否格式化 默认false-->*/
        performanceInterceptor.setFormat(false);
        return performanceInterceptor;
    }

    /**
     * @methodName  paginationInterceptor
     * @description  mybatis-plus分页插件
     * @param
     * @author  lixueyun
     * @Date  2019/4/20 19:03
     * @return  com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        return paginationInterceptor;
    }
}
