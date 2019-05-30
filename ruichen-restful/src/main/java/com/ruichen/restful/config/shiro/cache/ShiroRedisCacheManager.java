package com.ruichen.restful.config.shiro.cache;

import org.apache.shiro.cache.AbstractCacheManager;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @ClassName  ShiroRedisCacheManager
 * @Description  redis作为shiro缓存
 * @Date  2019/5/30 21:35
 * @author  lixueyun
 * @version  V1.0
 */

@Component
public class ShiroRedisCacheManager extends AbstractCacheManager {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    protected Cache createCache(String name) throws CacheException {
        return new ShiroRedisCache(redisTemplate, name);
    }
}
