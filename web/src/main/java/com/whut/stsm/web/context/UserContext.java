package com.whut.stsm.web.context;

import com.whut.stsm.web.configuration.security.MyUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 用户认证后SecurityContextHolder会保存认证信息
 *
 * Created by null on 2017/3/10.
 */
public class UserContext {

    /**
     * 获取当前认证用户名
     *
     * @return username
     */
    public static String getCurrentUsername() {
        MyUserDetails userDetails = getUserDetails();
        if (userDetails == null) {
            return null;
        }
        return userDetails.getUsername();
    }

    private static MyUserDetails getUserDetails() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof MyUserDetails) {
            return (MyUserDetails) principal;
        }
        return null;
    }

}
