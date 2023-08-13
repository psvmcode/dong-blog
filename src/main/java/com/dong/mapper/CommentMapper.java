package com.dong.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dong.vo.CommentVO;
import com.dong.vo.ReplyCountVO;
import com.dong.vo.ReplyVO;
import com.dong.vo.SystemCommentVO;
import com.dong.entity.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentMapper extends BaseMapper<Comment> {

    List<CommentVO> listComments(@Param("page") int page, @Param("size") int size, @Param("articleId") Long articleId);

    List<ReplyVO> listReplies(Integer id);

    ReplyCountVO listReplyCountByCommentId(Integer id);

    Page<SystemCommentVO> selectPageList(@Param("page") Page<Object> objectPage, @Param("keywords") String keywords);

}
