package com.dong.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dong.common.ResponseResult;
import com.dong.entity.Comment;
import com.dong.dto.CommentDTO;

import java.util.List;

public interface CommentService extends IService<Comment> {

    ResponseResult listComment(String keywords);

    ResponseResult deleteBatch(List<Integer> ids);

    //    ------web端方法开始------
    ResponseResult comments(Long articleId);

    ResponseResult addComment(CommentDTO comment);

    ResponseResult repliesByComId(Integer commentId);

}
