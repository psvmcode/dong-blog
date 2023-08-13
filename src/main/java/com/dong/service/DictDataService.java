package com.dong.service;

import com.dong.common.ResponseResult;
import com.dong.entity.DictData;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface DictDataService extends IService<DictData> {

    ResponseResult listDictData(Integer dictId, Integer isPublish);

    ResponseResult insertDictData(DictData dictData);

    ResponseResult updateDictData(DictData dictData);

    ResponseResult deleteBatch(List<Long> ids);

    ResponseResult deleteDictData(Long id);

    ResponseResult getDataByDictType(List<String> types);

}
