package com.zhuyaya.platform.monitor.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.zhuyaya.platform.common.entity.QueryRequest;
import com.zhuyaya.platform.monitor.entity.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.scheduling.annotation.Async;

/**
 * 日志处理接口
 *
 * @author zhengfeizhu
 * @version 1.0
 * @date 2022-01-30 11:13
 */
public interface ILogService extends IService<Log> {

    /**
     * 查询操作日志（分页）
     * @param log 日志对象
     * @param request 查询条件
     * @return IPage<Log>
     */
    IPage<Log> findLogs(Log log, QueryRequest request);

    /**
     * 删除操作日志
     * @param logsIds 日志ID数组
     */
    void deleteLogs(String[] logsIds);

    /**
     * 异步保存操作日志
     * @param point 切点
     * @param log 日志
     * @throws JsonProcessingException 异常
     */
    @Async("platformAsyncThreadPool")
    void saveLog(ProceedingJoinPoint point, Log log) throws JsonProcessingException;
}