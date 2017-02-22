package com.whut.stsm.common.util;

import com.google.gson.Gson;

/**
 * Json工具类
 *
 * Created by null on 2017/2/22.
 */
public class JsonUtil {

    private static final Gson gson = new Gson();

    public static String parse(Object obj) {
        return gson.toJson(obj);
    }

}
