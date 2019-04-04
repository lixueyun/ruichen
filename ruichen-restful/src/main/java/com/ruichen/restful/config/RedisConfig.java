package com.ruichen.restful.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @ClassName  RedisTemplate配置类
 * @Description
 * @author  lixueyun
 * @Date  2019/4/3 17:46
 */
@Configuration
public class RedisConfig {

    /**
     * @methodName  redisTemplate
     * @description RedisTemplate默认采用的是JDK的序列化策略保存的key和value都是采用此策略序列化保存的
     * 使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值
     * @param jedisConnectionFactory
     * @author  lixueyun
     * @Date  2019/4/4 10:56
     * @return  org.springframework.data.redis.core.RedisTemplate<java.lang.Object,java.lang.Object>
     */
    @Bean
    @ConditionalOnMissingBean(name = "redisTemplate")
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory jedisConnectionFactory) {
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.setConnectionFactory(jedisConnectionFactory);
        return template;
    }
}
