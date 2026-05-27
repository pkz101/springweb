package com.gedi.filter;

import com.gedi.utils.CurrentHolder;
import com.gedi.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
@WebFilter(urlPatterns = "/*")
public class TokenFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        if (isPublicRequest(request)) {
            chain.doFilter(request, response);
            return;
        }

        String jwt = extractToken(request);
        if (!StringUtils.hasText(jwt)) {
            writeUnauthorized(response, "Missing token");
            return;
        }

        try {
            Claims claims = JwtUtils.parseJWT(jwt);
            Object id = claims.get("id");
            if (id instanceof Number number) {
                CurrentHolder.setCurrentId(number.intValue());
            }
            chain.doFilter(request, response);
        } catch (Exception e) {
            log.info("Invalid token: {}", e.getMessage());
            writeUnauthorized(response, "Invalid token");
        } finally {
            CurrentHolder.remove();
        }
    }

    private boolean isPublicRequest(HttpServletRequest request) {
        return "OPTIONS".equalsIgnoreCase(request.getMethod()) || "/login".equals(request.getRequestURI());
    }

    private String extractToken(HttpServletRequest request) {
        String token = request.getHeader("token");
        if (StringUtils.hasText(token)) {
            return token;
        }

        String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (StringUtils.hasText(authorization) && authorization.startsWith("Bearer ")) {
            return authorization.substring(7);
        }
        return null;
    }

    private void writeUnauthorized(HttpServletResponse response, String message) throws IOException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write("{\"code\":0,\"msg\":\"" + message + "\"}");
    }
}
