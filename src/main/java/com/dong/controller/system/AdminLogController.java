package com.dong.controller.system;


import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import com.dong.annotation.OperationLogger;
import com.dong.common.ResponseResult;
import com.dong.service.AdminLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/adminLog")
@RequiredArgsConstructor
@Api(tags = "操作日志管理")
public class AdminLogController {

    private final AdminLogService adminLogService;

    @GetMapping(value = "/list")
    @SaCheckLogin
    @ApiOperation(value = "操作日志列表", httpMethod = "GET", response = ResponseResult.class, notes = "操作日志列表")
    public ResponseResult list() {
        return adminLogService.listAdminLog();
    }

    @DeleteMapping(value = "/delete")
    @OperationLogger(value = "删除操作日志")
    @SaCheckPermission("/system/adminLog/delete")
    @ApiOperation(value = "删除操作日志", httpMethod = "DELETE", response = ResponseResult.class, notes = "删除操作日志")
    public ResponseResult delete(@RequestBody List<Long> ids) {
        return adminLogService.deleteAdminLog(ids);
    }

}

