package com.zhuyaya.platform.common.utils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhuyaya.platform.common.entity.PlatformConstant;
import com.zhuyaya.platform.common.entity.QueryRequest;
import org.apache.commons.lang3.StringUtils;

/**
 * 排序工具类
 *
 * @author zhengfeizhu
 * @version 1.0
 * @date 2022-02-09 9:53
 */
@SuppressWarnings("unchecked")
public class SortUtil {

    /**
     * 处理排序（分页情况下）for mybatis-plus
     * @param request QueryRequest 查询请求
     * @param page 分页对象
     * @param defaultSort 默认排序字段
     * @param defaultOrder 默认排序规则
     * @param camelToUnderscore 是否开启驼峰转下划线
     */
    public static void handlePageSort(QueryRequest request, Page<?> page, String defaultSort, String defaultOrder, boolean camelToUnderscore) {
        page.setCurrent(request.getPageNum());
        page.setSize(request.getPageSize());
        String sortField = request.getField();
        if (camelToUnderscore) {
            sortField = PlatformUtil.camelToUnderscore(sortField);
            defaultSort = PlatformUtil.camelToUnderscore(defaultSort);
        }
        if (StringUtils.isNotBlank(sortField)
                && StringUtils.isNotBlank(request.getOrder())
                && !StringUtils.equalsIgnoreCase(sortField, "null")
                && !StringUtils.equalsIgnoreCase(request.getOrder(), "null")) {
            if (StringUtils.equals(request.getOrder(), PlatformConstant.ORDER_DESC)) {
                page.setDesc(sortField);
            } else {
                page.setAsc(sortField);
            }
        } else {
            if (StringUtils.isNotBlank(defaultSort)) {
                if (StringUtils.equals(defaultOrder, PlatformConstant.ORDER_DESC)) {
                    page.setDesc(defaultSort);
                } else {
                    page.setAsc(defaultSort);
                }
            }
        }
    }

    public static void handlePageSort(QueryRequest request, Page<?> page) {
        handlePageSort(request,page, null, null, false);
    }

    public static void handlePageSort(QueryRequest request, Page<?> page, boolean camelToUnderscore) {
        handlePageSort(request, page, null, null, camelToUnderscore);
    }

    /**
     * 处理排序 for mybatis-plus
     * @param request QueryRequest 查询请求
     * @param wrapper 查询条件（包装类）
     * @param defaultSort 默认排序字段
     * @param defaultOrder 默认排序规则
     * @param camelToUnderscore 是否开启驼峰转下划线
     */
    public static void handleWrapperSort(QueryRequest request, QueryWrapper<?> wrapper, String defaultSort, String defaultOrder, boolean camelToUnderscore) {
        String sortField = request.getField();
        if (camelToUnderscore) {
            sortField = PlatformUtil.camelToUnderscore(sortField);
            defaultSort = PlatformUtil.camelToUnderscore(defaultSort);
        }
        if (StringUtils.isNotBlank(sortField)
                && StringUtils.isNotBlank(request.getOrder())
                && !StringUtils.equalsIgnoreCase(sortField, "null")
                && !StringUtils.equalsIgnoreCase(request.getOrder(), "null")) {
            if (StringUtils.equals(request.getOrder(), PlatformConstant.ORDER_DESC)) {
                wrapper.orderByDesc(sortField);
            } else {
                wrapper.orderByAsc(sortField);
            }
        } else {
            if (StringUtils.isNotBlank(defaultSort)) {
                if (StringUtils.equals(defaultOrder, PlatformConstant.ORDER_DESC)) {
                    wrapper.orderByDesc(defaultSort);
                } else {
                    wrapper.orderByAsc(defaultSort);
                }
            }
        }
    }

    public static void handleWrapperSort(QueryRequest request, QueryWrapper<?> wrapper) {
        handleWrapperSort(request, wrapper, null, null, false);
    }

    public static void handleWrapperSort(QueryRequest request, QueryWrapper<?> wrapper, boolean camelToUnderscore) {
        handleWrapperSort(request, wrapper, null, null, camelToUnderscore);
    }
}