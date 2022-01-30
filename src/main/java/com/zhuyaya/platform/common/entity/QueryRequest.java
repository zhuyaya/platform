package com.zhuyaya.platform.common.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * mybatis-plus 查询条件（基础）
 *
 * @author zhengfeizhu
 * @version 1.0
 * @date 2022-01-30 13:51
 */
@Data
@ToString
public class QueryRequest implements Serializable {

    private static final long serialVersionUID = -904746437141009626L;

    /**
     * 当前页面数据量
     */
    private int pageSize = 10;

    /**
     * 当前页码
     */
    private int pageNum = 1;

    /**
     * 排序字段
     */
    private String field;

    /**
     * 排序方式 asc 升序， desc 降序
     */
    private String order;
}