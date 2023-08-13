package com.dong.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dong.entity.Tags;
import com.dong.common.ResponseResult;

import java.util.List;

public interface TagsService extends IService<Tags> {

    ResponseResult listTags(String name);

    ResponseResult insertTag(Tags tags);

    ResponseResult updateTag(Tags tags);

    ResponseResult deleteById(Long id);

    ResponseResult deleteBatch(List<Long> ids);

    ResponseResult getTagsById(Long id);

    ResponseResult top(Long id);


    //    -----web端方法开始-----
    ResponseResult webList();

}
