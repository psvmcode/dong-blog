package com.dong.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReplyCountVO {

    /**
     * 评论id
     */
    private Integer commentId;

    /**
     * 回复数量
     */
    private Integer replyCount;

}
