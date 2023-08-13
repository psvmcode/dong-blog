package com.dong.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dong.common.ResponseResult;
import com.dong.entity.FriendLink;

import java.util.List;

public interface FriendLinkService extends IService<FriendLink> {

    ResponseResult listFriendLink(String name, Integer status);

    ResponseResult insertFriendLink(FriendLink friendLink);

    ResponseResult updateFriendLink(FriendLink friendLink);

    ResponseResult deleteBatch(List<Integer> ids);

    ResponseResult top(Integer id);


    //    ----web端开始-----
    ResponseResult webFriendLinkList();

    ResponseResult applyFriendLink(FriendLink friendLink);

}
