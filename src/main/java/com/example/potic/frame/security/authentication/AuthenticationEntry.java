package com.example.potic.frame.security.authentication;

import com.alibaba.fastjson.JSONObject;
import com.example.potic.result.ResponseMessage;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 对登录用户的未授权请求进行处理
 */
@Component
public class AuthenticationEntry implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.write(JSONObject.toJSONString(ResponseMessage.fail("未登录,请先登录!")));
        out.flush();
        out.close();
    }
}
