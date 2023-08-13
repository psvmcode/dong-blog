package com.dong.service;


import com.dong.common.ResponseResult;
import com.dong.dto.LoginDTO;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public interface LoginService {

    Map<String, String> getCode(HttpServletResponse response) throws IOException;

    ResponseResult login(LoginDTO vo);

}
