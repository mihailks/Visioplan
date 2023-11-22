package com.visioplanserver.config;

import com.visioplanserver.web.interceptorTest.PlatformDetector;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfigurer implements WebMvcConfigurer {
    private final PlatformDetector platformDetector;

    public WebConfigurer(PlatformDetector platformDetector) {
        this.platformDetector = platformDetector;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(platformDetector).addPathPatterns("/file/**");
    }
}
