package com.yiyuclub.springsecurity.config;

import cn.hutool.json.JSONUtil;
import com.yiyuclub.springsecurity.utils.ResultData;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//身份未验证进入
@Component
public class SelfAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        ResultData resultData = new ResultData();

        resultData.setStatus(000);
        resultData.setMsg("need to login");
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        httpServletResponse.getWriter().write(JSONUtil.toJsonStr(resultData));

    }
}
