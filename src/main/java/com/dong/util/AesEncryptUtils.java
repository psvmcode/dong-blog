package com.dong.util;

import cn.dev33.satoken.secure.SaSecureUtil;

public class AesEncryptUtils {

    final static String key = "dong2023";

    /**
     * 校验内容是否一直
     */
    public static boolean validate(String target, String target1) {
        return target.equalsIgnoreCase(aesEncrypt(target1));
    }

    /**
     * AES加密
     *
     * @param password：密码
     * @return
     */
    public static String aesEncrypt(String password) {
        return SaSecureUtil.aesEncrypt(key, password);
    }

    public static void main(String[] args) {
        System.out.println(aesEncrypt("123456"));
    }

}
