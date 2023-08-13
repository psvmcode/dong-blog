package com.dong.service;

import com.dong.entity.Job;
import com.dong.common.ResponseResult;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dong.enums.TaskException;
import org.quartz.SchedulerException;

import java.util.List;

public interface JobService extends IService<Job> {

    ResponseResult listJob(String jobName, String jobGroup, String status);

    ResponseResult getJobById(Long jobId);

    ResponseResult insertJob(Job job) throws SchedulerException, TaskException;

    ResponseResult updateJob(Job job) throws SchedulerException, TaskException;

    ResponseResult deleteJob(Long jobId) throws SchedulerException;

    ResponseResult deleteBatch(List<Long> ids);

    ResponseResult pauseJob(Job job) throws SchedulerException;

    ResponseResult run(Job job);

    ResponseResult changeStatus(Job job) throws SchedulerException;

}
