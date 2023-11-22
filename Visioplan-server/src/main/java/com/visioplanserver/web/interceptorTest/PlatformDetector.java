package com.visioplanserver.web.interceptorTest;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class PlatformDetector implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        String userAgent = request.getHeader("User-Agent");

        if (userAgent.contains("Android") || userAgent.contains("iPhone")) {
            request.setAttribute("platform", "mobile");
        } else {
            request.setAttribute("platform", "desktop");
        }

        return true;
    }

}
