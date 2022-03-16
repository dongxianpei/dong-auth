package com.sansei.auth.web;


import com.alibaba.fastjson.JSON;
import com.sansei.base.rpc.Result;
import com.sansei.base.rpc.http.ResponseCode;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        response.getWriter().write(JSON.toJSONString(Result.fail(ResponseCode.UNAUTHORIZED)));

    }
}
