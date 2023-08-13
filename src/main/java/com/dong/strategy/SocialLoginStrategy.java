package com.dong.strategy;

import com.dong.vo.UserInfoVO;

public interface SocialLoginStrategy {
    /**
     * 登录
     *
     * @param data 数据
     * @return {@link UserInfoVO} 用户信息
     */
    UserInfoVO login(String data);
}
