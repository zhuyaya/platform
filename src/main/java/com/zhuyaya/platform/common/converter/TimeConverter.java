package com.zhuyaya.platform.common.converter;

import com.wuwenze.poi.convert.WriteConverter;
import com.wuwenze.poi.exception.ExcelKitWriteConverterException;
import com.zhuyaya.platform.common.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;

/**
 * Excel 导出时间类型字段格式化
 *
 * @author zhengfeizhu
 * @version 1.0
 * @date 2022-01-30 11:16
 */
@Slf4j
public class TimeConverter implements WriteConverter {
    @Override
    public String convert(Object value) throws ExcelKitWriteConverterException {
        if (value == null) {
            return "";
        } else {
            try {
                return DateUtil.formatCSTTime(value.toString(), DateUtil.FULL_TIME_SPLIT_PATTERN);
            } catch (ParseException e) {
                String message = "Excel导出过程中时间转换异常";
                log.error(message, e);
                throw new ExcelKitWriteConverterException(message);
            }
        }
    }
}