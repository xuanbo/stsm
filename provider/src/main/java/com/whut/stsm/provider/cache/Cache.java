package com.whut.stsm.provider.cache;

import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * 缓存接口
 * key-value的缓存
 *
 * Created by null on 2017/2/24.
 */
public interface Cache<String, V> {

    void put(String key, V value);

    void put(String key, V value, Long expired, TimeUnit unit);

    Optional<V> get(String key);

    void remove(String key);

    void remove(Collection<String> keys);

    boolean exist(String key);

}
