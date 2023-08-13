package com.dong.service;

import com.dong.entity.JobLog;
import com.dong.common.ResponseResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface JobLogService extends IService<JobLog> {

    ResponseResult listJobLog(String jobName, String jobGroup, String status, String startTime,
                              String endTime, Long jobId);

    ResponseResult deleteBatch(List<Long> ids);

    ResponseResult clean();

}
