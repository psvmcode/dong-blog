package com.dong.service;

import com.dong.common.ResponseResult;
import com.dong.entity.Message;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface MessageService extends IService<Message> {

    ResponseResult listMessage(String name);

    ResponseResult deleteMessageById(int id);

    ResponseResult passBatch(List<Integer> ids);

    ResponseResult deleteBatch(List<Integer> ids);

    //    ------web端方法开始-----
    ResponseResult webAddMessage(Message message);

    ResponseResult webMessage();

}
