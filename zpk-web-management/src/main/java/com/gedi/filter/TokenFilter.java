package com.gedi.filter;

import com.gedi.utils.CurrentHolder;
import com.gedi.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import java.io.IOException;

/**
 * 令牌校验过滤器
 */
@Slf4j
@WebFilter(urlPatterns = "/*")
public class TokenFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        //1. 获取请求 url。
        String url = request.getRequestURL().toString();

        //2. 判断请求 url 中是否包含 login，如果包含，说明是登录操作，放行。
        if(url.contains("login")){ //登录请求
            log.info("登录请求 , 直接放行");
            chain.doFilter(request, response);
            return;
        }

        //3. 获取请求头中的令牌（token）。
        String jwt = request.getHeader("token");

        //4. 判断令牌是否存在，如果不存在，返回错误结果（未登录）。
        if(!StringUtils.hasLength(jwt)){ //jwt 为空
            log.info("获取到 jwt 令牌为空，返回401状态码");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return;
        }

        //5. 解析 token，如果解析失败，返回错误结果（未登录）。
        try {
            Claims claims = JwtUtils.parseJWT(jwt);
            log.info("解析到用户信息: {}", claims);
            CurrentHolder.setCurrentId((Integer) claims.get("id"));

        } catch (Exception e) {
            log.info("解析令牌失败，返回错误结果");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return;
        }

        //6. 放行。
        log.info("令牌合法，放行");
        chain.doFilter(request , response);

        //7. 清空当前线程绑定的id
        CurrentHolder.remove();
    }

}
