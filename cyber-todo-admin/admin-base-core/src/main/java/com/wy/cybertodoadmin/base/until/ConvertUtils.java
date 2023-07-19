package com.wy.cybertodoadmin.base.until;

import com.wy.cybertodoadmin.core.constant.CommonConstant;
import lombok.extern.slf4j.Slf4j;

/**
 * @author WangYu
 * @project cyber-todo
 * @description
 * @date 2023/7/10 17:00:10
 */
@Slf4j
public class ConvertUtils {
    public static String getString(String s) {
        return (getString(s, ""));
    }

    public static String getString(String s, String defval) {
        if (isEmpty(s)) {
            return (defval);
        }
        return (s.trim());
    }

    public static boolean isEmpty(Object object) {
        if (object == null) {
            return (true);
        }
        if ("".equals(object)) {
            return (true);
        }
        if (CommonConstant.STRING_NULL.equals(object)) {
            return (true);
        }
        return (false);
    }

}
