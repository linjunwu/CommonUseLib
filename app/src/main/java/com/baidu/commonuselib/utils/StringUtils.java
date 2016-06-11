package com.baidu.commonuselib.utils;

import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * StringUtils
 *
 * @author linjunwu
 * @since 2016/6/4
 */
public class StringUtils {
    // 竖线分割的正则表达式
    private static final String  VERTICAL_BAR = "\\|";


    /**
     * 从一个字符串中，查找第一个出现数字的位置
     * @param str
     * @return
     */
    public static int findFirstNumInStringIndex(String str) {
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        Pattern pattern = Pattern.compile("[a-zA-Z0-9]");
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            return str.indexOf(matcher.group());
        }

        return -1;
    }
}
