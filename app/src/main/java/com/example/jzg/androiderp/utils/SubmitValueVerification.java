package com.example.jzg.androiderp.utils;

import android.text.TextUtils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by wangyd on 16/1/13.
 * 验证上传服务器的参数值是否为空工具类
 */
public final class SubmitValueVerification {

    /**
     * 验证value为String时是否为空校验
     *
     * @param params
     * @return
     */
    public static boolean isEmpty(Map<String, String> params) {
        if (params.size() == 0) return true;
        if (params == null) return true;
        for (String value : params.values()) {
            if (TextUtils.isEmpty(value))
                return true;
        }
        return false;
    }


    /**
     * 过滤传递的参数集合中的null值参数
     *
     * @param params
     * @return
     */
    public static Map<String, String> filterParams(Map<String, String> params) {
        Map<String, String> tempMap = new HashMap<>();
        if (params.size() == 0 || params == null) return tempMap;

        Iterator<Map.Entry<String, String>> iterator = params.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
            if (!TextUtils.isEmpty(entry.getValue()))
                tempMap.put(entry.getKey(), entry.getValue());
        }

        return tempMap;

    }
}
