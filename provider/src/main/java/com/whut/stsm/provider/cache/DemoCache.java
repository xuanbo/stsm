package com.whut.stsm.provider.cache;

import com.whut.stsm.common.dto.DemoDTO;
import com.whut.stsm.provider.dao.DemoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * demo缓存
 * 在service和dao之间加一层cache，如果为某个dao提供cache，则继承RedisCache
 *
 * Created by null on 2017/2/24.
 */
@Component
public class DemoCache extends RedisCache<DemoDTO> {

    @Value("${namespace.cache.demo}")
    private String namespace;

    @Autowired
    private DemoRepository demoRepository;

    public Optional<DemoDTO> findById(Long id) {
        final String key = namespace + "id_" + id;
        Optional<DemoDTO> cacheDemoDTOOptional = get(key);
        if (cacheDemoDTOOptional.isPresent()) {
            return cacheDemoDTOOptional;
        }
        DemoDTO persistDemoDTO = demoRepository.findOne(id);
        Optional<DemoDTO> persistDemoDTOOptional = Optional.ofNullable(persistDemoDTO);
        persistDemoDTOOptional.ifPresent(o -> put(key, o));
        return persistDemoDTOOptional;
    }

    public void save(DemoDTO demoDTO) {
        demoRepository.save(demoDTO);
    }

}
