package com.zhuyaya.platform.monitor.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.zhuyaya.platform.common.entity.QueryRequest;
import com.zhuyaya.platform.monitor.entity.Log;
import com.zhuyaya.platform.monitor.mapper.LogMapper;
import com.zhuyaya.platform.monitor.service.ILogService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 日志 service 实现类
 *
 * @author zhengfeizhu
 * @version 1.0
 * @date 2022-01-30 16:46
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements ILogService {
    @Override
    public IPage<Log> findLogs(Log log, QueryRequest request) {
        return null;
    }

    @Override
    public void deleteLogs(String[] logsIds) {

    }

    @Override
    public void saveLog(ProceedingJoinPoint point, Log log) throws JsonProcessingException {

    }
}