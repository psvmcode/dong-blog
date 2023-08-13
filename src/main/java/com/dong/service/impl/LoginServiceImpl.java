package com.dong.service.impl;

import cn.dev33.satoken.stp.SaLoginModel;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.codec.Base64;
import com.google.code.kaptcha.Producer;
import com.dong.common.RedisConstants;
import com.dong.common.ResponseResult;
import com.dong.common.Constants;
import com.dong.entity.User;
import com.dong.exception.BusinessException;
import com.dong.mapper.UserMapper;
import com.dong.service.LoginService;
import com.dong.service.RedisService;
import com.dong.util.AesEncryptUtils;
import com.dong.dto.LoginDTO;
import com.dong.util.UUIDUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.FastByteArrayOutputStream;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.dong.common.ResultCode.ERROR_PASSWORD;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LoginServiceImpl implements LoginService {

    @Resource(name = "captchaProducerMath")
    private Producer captchaProducerMath;

    private final UserMapper userMapper;

    private final RedisService redisService;

    @Override
    public Map<String, String> getCode(HttpServletResponse response) throws IOException {
        Map<String, String> result = new HashMap<String, String>();
        // 生成验证码的UUID
        String uuid = UUIDUtils.getUuid();
        String capStr = null;
        String code = null;
        BufferedImage image = null;
        // 生成验证码
        String capText = captchaProducerMath.createText();
        capStr = capText.substring(0, capText.lastIndexOf("@"));
        code = capText.substring(capText.lastIndexOf("@") + 1);
        image = captchaProducerMath.createImage(capStr);
        redisService.setCacheObject(RedisConstants.CAPTCHA_CODE + uuid, code, RedisConstants.CAPTCHA_EXPIRATION, TimeUnit.MINUTES);
        // 转换流信息写出
        FastByteArrayOutputStream os = new FastByteArrayOutputStream();
        ImageIO.write(image, "jpg", os);
        result.put("uuid", uuid);
        result.put("img", Base64.encode(os.toByteArray()));
        return result;
    }

    @Override
    public ResponseResult login(LoginDTO dto) {
        //先校验验证码
        Object cacheObject = redisService.getCacheObject(RedisConstants.CAPTCHA_CODE + dto.getUuid());
        if (cacheObject == null || !cacheObject.equals(dto.getCode())) {
            throw new BusinessException("验证码已失效或回答错误!");
        }
        //校验用户名和密码
        User user = userMapper.selectNameAndPassword(dto.getUsername(), AesEncryptUtils.aesEncrypt(dto.getPassword()));
        Assert.isTrue(user != null, ERROR_PASSWORD.getDesc());

        if (dto.getRememberMe()) {
            StpUtil.login(user.getId().longValue(), new SaLoginModel().setTimeout(60 * 60 * 24 * 7));
        } else {
            StpUtil.login(user.getId().longValue());
        }
        StpUtil.getSession().set(Constants.CURRENT_USER, userMapper.getById(user.getId()));
        return ResponseResult.success(StpUtil.getTokenValue());
    }
}
