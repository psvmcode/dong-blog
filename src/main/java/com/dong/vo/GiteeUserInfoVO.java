package com.dong.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GiteeUserInfoVO {

    /**
     * 昵称
     */
    private String name;

    /**
     * 头像
     */
    private String avatar_url;

    /**
     * id
     */
    private String id;

}
