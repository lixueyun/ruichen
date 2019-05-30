package com.ruichen.restful.config.shiro.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Collection;
import java.util.Set;

/**
 * @ClassName  ShiroRedisCache
 * @Description  redis整合shiro
 * @Date  2019/5/30 21:39
 * @author  lixueyun
 * @version  V1.0
 */
public class ShiroRedisCache<K, V>  implements Cache<K, V> {

    private RedisTemplate redisTemplate;

    private String prefix;

    public ShiroRedisCache(RedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;
    }

    public ShiroRedisCache(RedisTemplate redisTemplate, String prefix) {
        this(redisTemplate);
        this.prefix = prefix;
    }

    @Override
    public V get(K k) throws CacheException {
        if (k == null) {
            return null;
        }
        return (V)redisTemplate.opsForValue().get(k);
    }

    @Override
    public V put(K k, V v) throws CacheException {
        if (k== null || v == null) {
            return null;
        }
        redisTemplate.opsForValue().set(k, v);
        return v;
    }

    @Override
    public V remove(K k) throws CacheException {
        if(k==null){
            return null;
        }
        V v = (V)redisTemplate.opsForValue().get(k);
        redisTemplate.delete(k);
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
        Set<K> sets = redisTemplate.keys(this.prefix + ":");
        return sets;
    }

    @Override
    public Collection<V> values() {
        Set<K> keys = keys();
        return redisTemplate.opsForValue().multiGet(keys);
    }
}
