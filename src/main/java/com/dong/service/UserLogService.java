package com.dong.service;

import com.dong.common.ResponseResult;
import com.dong.entity.UserLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface UserLogService extends IService<UserLog> {

    ResponseResult listUserLog();

    ResponseResult deleteBatch(List<Long> ids);

}
