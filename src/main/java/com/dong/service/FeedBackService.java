package com.dong.service;

import com.dong.common.ResponseResult;
import com.dong.entity.FeedBack;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface FeedBackService extends IService<FeedBack> {

    ResponseResult listFeedBack(Integer type);

    ResponseResult deleteBatch(List<Integer> ids);


    ResponseResult insertFeedback(FeedBack feedBack);

}
