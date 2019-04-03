package com.ruichen.restful.config;

import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @ClassName  RedisTemplate配置类
 * @Description
 * @author  lixueyun
 * @Date  2019/4/3 17:46
 */
@Data
@Configuration
public class RedisConfig {

    private String masterName;

    private String Sentinel1;

    private String Sentinel2;

    private int port1;

    private int port2;

    /**
     * 
        * @methodName: jedisConnectionFactory
        * @Description: 设置默认为jedisConnectionFactory
        * @return
        * @author whf
        * @date 2019年1月2日
     */
    @Bean
    public RedisConnectionFactory jedisConnectionFactory() {
        RedisSentinelConfiguration configuration = new RedisSentinelConfiguration().master(masterName)
                .sentinel(Sentinel1, port1).sentinel(Sentinel2, port2);
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        // 资源池中最大连接数
        jedisPoolConfig.setMaxTotal(50);
        //资源池允许最大空闲的连接数
        jedisPoolConfig.setMaxIdle(50);
        // 最小空闲连接数
        jedisPoolConfig.setMinIdle(20);
        // 当池内没有可用连接时，最大等待时间
        jedisPoolConfig.setMaxWaitMillis(1000);
        // 其他属性可以自行添加
        return new JedisConnectionFactory(configuration, jedisPoolConfig);
    }

    /**
     * 
        * @methodName: redisTemplate
        * @Description: RedisTemplate默认采用的是JDK的序列化策略保存的key和value都是采用此策略序列化保存的
        * 使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值
        * @param jedisConnectionFactory
        * @return
        * @author whf
        * @date 2019年1月2日
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
