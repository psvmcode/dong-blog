package com.dong.service;

import com.dong.common.ResponseResult;
import com.dong.entity.Page;
import com.baomidou.mybatisplus.extension.service.IService;

public interface PageService extends IService<Page> {

    ResponseResult listPage();

    ResponseResult insertPage(Page page);

    ResponseResult updatePage(Page page);

    ResponseResult deletePageById(Long id);

}
