package com.sansei.auth.web.access;


import com.alibaba.fastjson.JSON;
import com.sansei.base.rpc.Result;
import com.sansei.base.rpc.http.ResponseCode;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        response.getWriter().write(JSON.toJSONString(Result.fail(ResponseCode.FORBIDDEN)));

    }
}
