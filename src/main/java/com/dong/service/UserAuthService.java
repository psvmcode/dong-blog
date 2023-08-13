package com.dong.service;

import com.dong.common.ResponseResult;
import com.dong.entity.UserAuth;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dong.dto.EmailLoginDTO;
import com.dong.dto.EmailRegisterDTO;
import com.dong.dto.QQLoginDTO;
import com.dong.dto.UserAuthDTO;

public interface UserAuthService extends IService<UserAuth> {

    ResponseResult emailRegister(EmailRegisterDTO emailRegisterDTO);

    ResponseResult updatePassword(EmailRegisterDTO emailRegisterDTO);

    ResponseResult emailLogin(EmailLoginDTO emailLoginDTO);

    ResponseResult qqLogin(QQLoginDTO qqLoginDTO);

    ResponseResult weiboLogin(String code);

    ResponseResult giteeLogin(String code);

    ResponseResult sendEmailCode(String email);

    ResponseResult bindEmail(UserAuthDTO vo);

    ResponseResult updateUser(UserAuthDTO vo);

}
