package com.zhuyaya.platform.common.configure;

import com.p6spy.engine.spy.appender.MessageFormattingStrategy;
import com.zhuyaya.platform.common.utils.DateUtil;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;

/**
 * 自定义 p6spy sql 输出格式
 *
 * @author zhengfeizhu
 * @version 1.0
 * @date 2022-02-08 15:42
 */
public class P6spySqlFormatConfigure implements MessageFormattingStrategy {

    /** 过滤掉定时任务的 SQL */
    @Override
    public String formatMessage(int connectionId, String now, long elapsed, String category, String prepared, String sql, String url) {
        return StringUtils.isNotBlank(sql) ? DateUtil.formatFullTime(LocalDateTime.now(), DateUtil.FULL_TIME_SPLIT_PATTERN)
                + " | 耗时 " + elapsed + " ms | SQL 语句: " + StringUtils.LF + sql.replaceAll("[\\s]+", StringUtils.SPACE) + ";" : StringUtils.EMPTY;
    }
}