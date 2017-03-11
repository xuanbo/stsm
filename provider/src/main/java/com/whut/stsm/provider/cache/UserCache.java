package com.whut.stsm.provider.cache;

import com.whut.stsm.common.dto.UserDTO;
import com.whut.stsm.provider.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

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

    @Autowired
    private RedisTemplate redisTemplate;

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

    public void loginFailure(String username) {
        final String key = namespace + "username_" + username + "_attemptTimes";
        BoundValueOperations valueOps = redisTemplate.boundValueOps(key);
        Object obj = valueOps.get();
        if (obj == null) {
            valueOps.set(1, 30, TimeUnit.MINUTES);
        } else if (obj instanceof Integer) {
            Integer attemptTimes = (Integer) obj;
            valueOps.set(++attemptTimes, 30, TimeUnit.MINUTES);
        }
    }

    public int getLoginAttemptTimes(String username) {
        final String key = namespace + "username_" + username + "_attemptTimes";
        BoundValueOperations valueOps = redisTemplate.boundValueOps(key);
        Object obj = valueOps.get();
        if (obj instanceof Integer) {
            return (int) obj;
        }
        return -1;
    }

    public void resetLoginAttemptTimes(String username) {
        final String key = namespace + "username_" + username + "_attemptTimes";
        redisTemplate.delete(key);
    }

    public List<UserDTO> findAll(List<Long> ids) {
        return userRepository.findAll(ids);
    }

    /**
     * 根据username模糊查询在给定团队中的用户，只取前10条
     *
     * @param usernameLike 查询的username
     * @param teamIds 在所在的团队中查询
     * @return List<UserDTO>
     */
    public List<UserDTO> findByUsernameLike(String usernameLike, List<Long> teamIds) {
        return userRepository.findByUsernameLikeAndTeamIdIn(usernameLike, teamIds);
    }
}
