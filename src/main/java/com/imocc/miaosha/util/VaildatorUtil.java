package com.imocc.miaosha.util;

import com.alibaba.druid.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 判断格式
 * @author Dyson
 * @date 2019/8/11 9:52
 */

public class VaildatorUtil {
    private static final Pattern mobile_pattern = Pattern.compile("1\\d{10}");
    public static boolean isMobile(String src){
        if (StringUtils.isEmpty(src)) {

            return false;
        }
        Matcher m = mobile_pattern.matcher(src);
        return m.matches();
    }

    public static void main(String[] args) {
        System.out.println(isMobile("12345678901"));
        System.out.println(isMobile("123445"));
    }
}
