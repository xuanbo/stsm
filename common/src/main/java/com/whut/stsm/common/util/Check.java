package com.whut.stsm.common.util;

import java.util.Collection;
import java.util.Map;

/**
 * 空判断
 *
 * Created by null on 2017/2/28.
 */
public class Check {

    /********************************************************
     *  string
     *******************************************************/
    public static boolean isNull(String value) {
        return value == null;
    }

    public static boolean isNotNull(String value) {
        return !isNull(value);
    }

    public static boolean isEmpty(String value) {
        return isNull(value) || value.isEmpty();
    }

    public static boolean isNotEmpty(String value) {
        return !isEmpty(value);
    }

    /********************************************************
     *  collection
     *******************************************************/
    public static boolean isNull(Collection<?> collection) {
        return collection == null;
    }

    public static boolean isNotNull(Collection<?> collection) {
        return !isNull(collection);
    }

    public static boolean isEmpty(Collection<?> collection) {
        return isNull(collection) || collection.isEmpty();
    }

    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }

    /********************************************************
     *  map
     *******************************************************/
    public static boolean isNull(Map map) {
        return map == null;
    }

    public static boolean isNotNull(Map map) {
        return !isNull(map);
    }

    public static boolean isEmpty(Map map) {
        return isNull(map) || map.isEmpty();
    }

    public static boolean isNotEmpty(Map map) {
        return !isEmpty(map);
    }

    /********************************************************
     *  object
     *******************************************************/
    public static boolean isNull(Object obj) {
        return obj == null;
    }

    public static boolean isNotNull(Object obj) {
        return !isNull(obj);
    }
}
