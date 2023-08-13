package com.dong.service;

import com.dong.common.ResponseResult;
import com.dong.entity.Dict;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface DictService extends IService<Dict> {

    ResponseResult listDict(String name, Integer isPublish, String descColumn, String ascColumn);

    ResponseResult insertDict(Dict dict);

    ResponseResult updateDict(Dict dict);

    ResponseResult deleteDict(int id);

    ResponseResult deleteBatch(List<Long> list);

}
