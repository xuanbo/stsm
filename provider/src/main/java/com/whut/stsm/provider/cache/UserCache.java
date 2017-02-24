package com.whut.stsm.provider.cache;

import com.whut.stsm.common.dto.UserDTO;
import com.whut.stsm.provider.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * user缓存
 *
 * Created by null on 2017/2/24.
 */
@Component
public class UserCache extends RedisCache<UserDTO> {

    @Value("${namespace.cache.user}")
    private String namespace;

    @Autowired
    private UserRepository userRepository;

    public Optional<UserDTO> findByUsername(String username) {
        final String key = namespace + "username_" + username;
        Optional<UserDTO> cacheUserDTOOptional = get(key);
        if (cacheUserDTOOptional.isPresent()) {
            return cacheUserDTOOptional;
        }
        UserDTO persistUserDTO = userRepository.findByUsername(username);
        Optional<UserDTO> persistUserDTOOptional = Optional.ofNullable(persistUserDTO);
        persistUserDTOOptional.ifPresent(o -> put(key, o));
        return persistUserDTOOptional;
    }

    public UserDTO save(UserDTO userDTO) {
        final String key = namespace + "username_" + userDTO.getUsername();
        UserDTO savedUserDTO = userRepository.save(userDTO);
        put(key, savedUserDTO);
        return savedUserDTO;
    }

}
