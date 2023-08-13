package com.dong.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dong.common.*;
import com.dong.exception.BusinessException;
import com.dong.service.*;
import com.dong.vo.UserInfoVO;
import com.dong.entity.User;
import com.dong.entity.UserAuth;
import com.dong.entity.WebConfig;
import com.dong.enums.LoginTypeEnum;
import com.dong.enums.UserStatusEnum;
import com.dong.mapper.UserAuthMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dong.strategy.context.SocialLoginStrategyContext;
import com.dong.util.*;
import com.dong.dto.EmailLoginDTO;
import com.dong.dto.EmailRegisterDTO;
import com.dong.dto.QQLoginDTO;
import com.dong.dto.UserAuthDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.mail.MessagingException;
import java.util.regex.Pattern;

import static com.dong.common.ResultCode.*;
import static com.dong.common.ResultCode.ERROR_MUST_REGISTER;
import static com.dong.common.FieldConstants.LIMIT_ONE;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserAuthServiceImpl extends ServiceImpl<UserAuthMapper, UserAuth> implements UserAuthService {

    private final SocialLoginStrategyContext socialLoginStrategyContext;

    private final EmailService emailService;

    private final UserService userService;

    private final RedisService redisService;

    private final WebConfigService webConfigService;

    /**
     * 邮箱账号注册
     *
     * @param vo
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult emailRegister(EmailRegisterDTO vo) {

        checkEmail(vo.getEmail());

        checkCode(RedisConstants.EMAIL_CODE + vo.getEmail(), vo.getCode());

        User user = getByUserName(vo.getEmail());
        Assert.isNull(user, EMAIL_IS_EXIST.getDesc());

        WebConfig config = webConfigService.getOne(new QueryWrapper<WebConfig>().last(LIMIT_ONE));
        UserAuth auth = UserAuth.builder().email(vo.getEmail()).avatar(config.getTouristAvatar()).nickname(vo.getNickname()).build();
        baseMapper.insert(auth);

        user = User.builder().username(vo.getEmail()).roleId(Constants.USER_ROLE_ID).userAuthId(auth.getId()).loginType(LoginTypeEnum.EMAIL.getType())
                .password(AesEncryptUtils.aesEncrypt(vo.getPassword())).build();
        boolean insert = userService.save(user);

        redisService.deleteObject(RedisConstants.EMAIL_CODE + vo.getEmail());

        return insert ? ResponseResult.success("注册成功") : ResponseResult.error(ERROR_DEFAULT.getDesc());
    }

    /**
     * 修改密码
     *
     * @param vo
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult updatePassword(EmailRegisterDTO vo) {

        checkEmail(vo.getEmail());
        checkCode(RedisConstants.EMAIL_CODE + vo.getEmail(), vo.getCode());

        User user = getByUserName(vo.getEmail());
        Assert.notNull(user, ERROR_MUST_REGISTER.getDesc());

        user.setPassword(AesEncryptUtils.aesEncrypt(vo.getPassword()));
        boolean update = userService.updateById(user);

        redisService.deleteObject(RedisConstants.EMAIL_CODE + vo.getEmail());

        return update ? ResponseResult.success("修改成功") : ResponseResult.error(ERROR_DEFAULT.getDesc());
    }

    /**
     * 邮箱登录
     *
     * @param vo
     * @return
     */
    @Override
    public ResponseResult emailLogin(EmailLoginDTO vo) {

        checkEmail(vo.getEmail());

        User user = getByUserName(vo.getEmail());
        if (user == null) {
            throw new BusinessException(ERROR_MUST_REGISTER.desc);
        }
        Assert.isTrue(user.getStatus() == UserStatusEnum.normal.code, EMAIL_DISABLE_LOGIN.getDesc());

        boolean validate = AesEncryptUtils.validate(user.getPassword(), vo.getPassword());
        Assert.isTrue(validate, ERROR_PASSWORD.getDesc());

        UserAuth auth = baseMapper.selectById(user.getUserAuthId());

        //登录
        StpUtil.login(user.getId().longValue());

        //组装数据
        UserInfoVO userInfoVO = UserInfoVO.builder().id(user.getId()).userInfoId(auth.getId()).avatar(auth.getAvatar()).nickname(auth.getNickname())
                .intro(auth.getIntro()).webSite(auth.getWebSite()).email(user.getUsername()).loginType(user.getLoginType()).token(StpUtil.getTokenValue()).build();

        return ResponseResult.success(userInfoVO);
    }

    @Override
    public ResponseResult qqLogin(QQLoginDTO qqLoginDTO) {
        UserInfoVO userInfoVO = socialLoginStrategyContext.executeLoginStrategy(JSON.toJSONString(qqLoginDTO), LoginTypeEnum.QQ);
        return ResponseResult.success(userInfoVO);
    }

    @Override
    public ResponseResult weiboLogin(String code) {
        UserInfoVO userInfoVO = socialLoginStrategyContext.executeLoginStrategy(code, LoginTypeEnum.WEIBO);
        return ResponseResult.success(userInfoVO);
    }

    @Override
    public ResponseResult giteeLogin(String code) {
        UserInfoVO userInfoVO = socialLoginStrategyContext.executeLoginStrategy(code, LoginTypeEnum.GITEE);
        return ResponseResult.success(userInfoVO);
    }

    /**
     * 用户绑定邮箱，发送验证码
     *
     * @param email
     * @return
     */
    @Override
    public ResponseResult sendEmailCode(String email) {
        try {
            emailService.sendCode(email);
            return ResponseResult.success("验证码已发送，请前往邮箱查看!!");
        } catch (MessagingException e) {
            e.printStackTrace();
            return ResponseResult.error(ERROR_DEFAULT.getDesc());
        }

    }

    /**
     * 绑定邮箱
     *
     * @param vo
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult bindEmail(UserAuthDTO vo) {
        String key = RedisConstants.EMAIL_CODE + vo.getEmail();
        checkCode(key, vo.getCode());

        UserAuth userAuth = getUserAuth();
        userAuth.setEmail(vo.getEmail());
        boolean update = updateById(userAuth);
        redisService.deleteObject(key);
        return update ? ResponseResult.success("绑定邮箱成功") : ResponseResult.error(ERROR_DEFAULT.getDesc());
    }

    /**
     * 修改用户信息
     *
     * @param vo
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult updateUser(UserAuthDTO vo) {
        UserAuth userAuth = getUserAuth();
        userAuth.setNickname(vo.getNickname());
        userAuth.setWebSite(vo.getWebSite());
        userAuth.setEmail(vo.getEmail());
        userAuth.setIntro(vo.getIntro());
        userAuth.setAvatar(vo.getAvatar());

        boolean update = updateById(userAuth);
        return update ? ResponseResult.success("修改信息成功") : ResponseResult.error(ERROR_DEFAULT.getDesc());
    }


    //---------------自定义方法开始-------------
    private UserAuth getUserAuth() {
        User user = userService.getById(StpUtil.getLoginIdAsInt());
        return baseMapper.selectById(user.getUserAuthId());
    }

    public void checkEmail(String email) {
        boolean matches = Pattern.compile("\\w+@{1}\\w+\\.{1}\\w+").matcher(email).matches();
        Assert.isTrue(matches, EMAIL_ERROR.getDesc());
    }

    public User getByUserName(String username) {
        return userService.getOne(new QueryWrapper<User>().eq(FieldConstants.USERNAME, username));
    }

    private void checkCode(String key, String sourCode) {
        Object code = redisService.getCacheObject(key);
        Assert.isTrue(code != null && code.equals(sourCode), ERROR_EXCEPTION_MOBILE_CODE.getDesc());
    }

}
