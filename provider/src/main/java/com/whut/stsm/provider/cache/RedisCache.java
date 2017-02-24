package com.whut.stsm.provider.cache;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * Cache接口的redis实现
 *
 * Created by null on 2017/2/24.
 */
public class RedisCache<V> implements Cache<String, V>, InitializingBean {

    @Autowired
    private RedisTemplate redisTemplate;

    private ValueOperations<String, V> valueOperations;

    @Override
    public void put(String key, V value) {
        valueOperations.set(key, value);
    }

    @Override
    public void put(String key, V value, Long expired, TimeUnit unit) {
        valueOperations.set(key, value, expired, unit);
    }

    @Override
    public Optional<V> get(String key) {
        return Optional.ofNullable(valueOperations.get(key));
    }

    @Override
    public void remove(String key) {
        redisTemplate.delete(key);
    }

    @Override
    public void remove(Collection<String> keys) {
        redisTemplate.delete(keys);
    }

    @Override
    public boolean exist(String key) {
        return redisTemplate.hasKey(key);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        valueOperations = redisTemplate.opsForValue();
    }
}
