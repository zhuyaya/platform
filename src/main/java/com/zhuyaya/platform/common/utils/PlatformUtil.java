package com.zhuyaya.platform.common.utils;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

/**
 * 基础工具类
 *
 * @author zhengfeizhu
 * @version 1.0
 * @date 2022-01-29 10:52
 */
@Slf4j
public class PlatformUtil {

    /**
     *  大驼峰转下划线
     * @param value 待处理字符串
     * @return 转换后的字符串
     */
    public static String camelToUnderscore(String value) {
        if (StringUtils.isBlank(value)) {
            return value;
        }
        String[] arr = StringUtils.splitByCharacterTypeCamelCase(value);
        if (arr.length == 0) {
            return value;
        }
        StringBuilder result = new StringBuilder();
        IntStream.range(0, arr.length).forEach(i -> {
            if (i != arr.length - 1) {
                result.append(arr[i]).append(StringPool.UNDERSCORE);
            } else {
                result.append(arr[i]);
            }
        });
        return StringUtils.lowerCase(result.toString());
    }

    /**
     * 下划线转大驼峰
     * @param value 待转换值
     * @return 结果
     */
    public static String underscoreToCamel(String value) {
        if (StringUtils.isBlank(value)) {
            return value;
        }
        String[] arr = value.split("_");
        if (arr.length == 0) {
            return value;
        }
        StringBuilder result = new StringBuilder();
        for (String s: arr) {
            result.append((String.valueOf(s.charAt(0))).toUpperCase()).append(s.substring(1));
        }
        return result.toString();
    }

    /**
     * 判断是否为 ajax 请求
     * @param request http 请求
     * @return 结果
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {
        return (request.getHeader("X-Requested-With") != null
                && "XMLHttpRequest".equals(request.getHeader("X-Requested-With")));
    }

    /**
     * 正则校验
     * @param regex 正则表达式
     * @param value 待匹配值
     * @return 正则结果
     */
    public static boolean match(String regex, String value) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }
}