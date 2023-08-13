package com.dong.service;

import com.dong.common.ResponseResult;
import com.dong.entity.SystemConfig;
import com.baomidou.mybatisplus.extension.service.IService;

public interface SystemConfigService extends IService<SystemConfig> {

    ResponseResult getConfig();

    ResponseResult updateConfig(SystemConfig systemConfig);

    SystemConfig getCustomizeOne();

}
