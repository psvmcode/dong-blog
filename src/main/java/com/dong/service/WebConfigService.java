package com.dong.service;

import com.dong.common.ResponseResult;
import com.dong.entity.WebConfig;
import com.baomidou.mybatisplus.extension.service.IService;

public interface WebConfigService extends IService<WebConfig> {

    ResponseResult listWebConfig();

    ResponseResult updateWebConfig(WebConfig webConfig);

}
