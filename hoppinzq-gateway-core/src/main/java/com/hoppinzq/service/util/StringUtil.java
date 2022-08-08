package com.hoppinzq.service.util;

public class StringUtil {
    public static boolean isNotEmpty(Object obj) {
        return obj != null && !"".equals(obj.toString().trim());
    }

}

