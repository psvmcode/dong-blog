package com.dong.strategy.context;

import com.dong.vo.UserInfoVO;
import com.dong.enums.LoginTypeEnum;
import com.dong.strategy.SocialLoginStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @apiNote 第三方登录策略上下文
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SocialLoginStrategyContext {

    private final Map<String, SocialLoginStrategy> socialLoginStrategyMap;

    /**
     * 执行第三方登录策略
     *
     * @param data          数据
     * @param loginTypeEnum 登录枚举类型
     * @return {@link UserInfoVO} 用户信息
     */
    public UserInfoVO executeLoginStrategy(String data, LoginTypeEnum loginTypeEnum) {
        return socialLoginStrategyMap.get(loginTypeEnum.getStrategy()).login(data);
    }

}
