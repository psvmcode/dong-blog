package com.dong.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dong.common.ResponseResult;
import com.dong.common.FieldConstants;
import com.dong.entity.AdminLog;
import com.dong.mapper.AdminLogMapper;
import com.dong.service.AdminLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dong.util.PageUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminLogServiceImpl extends ServiceImpl<AdminLogMapper, AdminLog> implements AdminLogService {

    /**
     * 分页查询操作日志
     *
     * @return
     */
    @Override
    public ResponseResult listAdminLog() {
        Page<AdminLog> sysLogPage = baseMapper.selectPage(new Page<>(PageUtils.getPageNo(), PageUtils.getPageSize()),
                new QueryWrapper<AdminLog>().orderByDesc(FieldConstants.CREATE_TIME));
        return ResponseResult.success(sysLogPage);
    }

    /**
     * 批量删除操作日志
     *
     * @param ids 操作日志id集合
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult deleteAdminLog(List<Long> ids) {
        int rows = baseMapper.deleteBatchIds(ids);
        return rows > 0 ? ResponseResult.success() : ResponseResult.error("批量删除操作日志失败");
    }

}
