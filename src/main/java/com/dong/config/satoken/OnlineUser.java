package com.dong.config.satoken;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.dong.util.DateUtils;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class OnlineUser {

    private String loginId;

    private Long userId;

    private String nickname;

    private String avatar;

    private String ip;

    private String os;

    private String city;

    private String browser;

    private String tokenValue;

    /**
     * 登录时间
     */
    @JsonFormat(pattern = DateUtils.FORMAT_STRING, timezone = "GMT+8")
    private Date loginTime;

    /**
     * 最近一次操作时间
     */
    @JsonFormat(pattern = DateUtils.FORMAT_STRING, timezone = "GMT+8")
    private Date lastActivityTime;

}

