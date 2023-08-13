package com.dong.service;

import com.dong.common.ResponseResult;
import com.dong.entity.AdminLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface AdminLogService extends IService<AdminLog> {

    /**
     * 分页查询操作日志
     *
     * @return
     */
    ResponseResult listAdminLog();

    /**
     * 批量删除操作日志
     *
     * @param ids 操作日志id集合
     * @return
     */
    ResponseResult deleteAdminLog(List<Long> ids);

}
