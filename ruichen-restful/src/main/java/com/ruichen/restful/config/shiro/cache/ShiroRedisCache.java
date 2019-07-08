package com.ruichen.restful.config.shiro.cache;

import com.ruichen.restful.common.constant.ShiroConstant;
import com.ruichen.restful.common.utils.JwtUtil;
import com.ruichen.restful.config.shiro.ShiroProperties;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName  ShiroRedisCache
 * @Description  redis整合shiro
 * @Date  2019/5/30 21:39
 * @author  lixueyun
 * @version  V1.0
 */
public class ShiroRedisCache<K, V>  implements Cache<K, V> {

    private RedisTemplate redisTemplate;


    public ShiroRedisCache(RedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;
    }

    /**
     * @methodName  getKey
     * @description 缓存的key名称获取为shiro:cache:account
     * @param key
     * @author  lixueyun
     * @Date  2019/7/8 14:36
     * @return  java.lang.String
     */
    private String getKey(Object key) {
        return ShiroConstant.PREFIX_SHIRO_CACHE + JwtUtil.getClaim(key.toString(), ShiroConstant.ACCOUNT);
    }

    @Override
    public V get(K k) throws CacheException {
        if (k == null) {
            return null;
        }
        return (V)redisTemplate.opsForValue().get(this.getKey(k));
    }

    @Override
    public V put(K k, V v) throws CacheException {
        if (k== null || v == null) {
            return null;
        }
        redisTemplate.opsForValue().set(this.getKey(k), v, Long.valueOf(ShiroProperties.SHIRO_CACHE_EXPIRE_TIME), TimeUnit.SECONDS);
        return v;
    }

    @Override
    public V remove(K k) throws CacheException {
        if(k==null){
            return null;
        }
        V v = (V)redisTemplate.opsForValue().get(this.getKey(k));
        redisTemplate.delete(this.getKey(k));
        return v;
    }

    @Override
    public void clear() throws CacheException {
        redisTemplate.getConnectionFactory().getConnection().flushDb();
    }

    @Override
    public int size() {
        return redisTemplate.getConnectionFactory().getConnection().dbSize().intValue();
    }

    @Override
    public Set<K> keys() {
        Set<K> sets = redisTemplate.keys(ShiroConstant.PREFIX_SHIRO_CACHE + ":");
        return sets;
    }

    @Override
    public Collection<V> values() {
        Set<K> keys = keys();
        return redisTemplate.opsForValue().multiGet(keys);
    }
}
