package com.whut.stsm.provider.util;

import com.whut.stsm.common.util.Page;

/**
 * Created by null on 2017/3/7.
 */
public class PageHelper {

    public static <T> void toPage(org.springframework.data.domain.Page<T> page, Page<T> toPage) {
        toPage.setCount(page.getTotalElements());
        toPage.setList(page.getContent());
    }

}
