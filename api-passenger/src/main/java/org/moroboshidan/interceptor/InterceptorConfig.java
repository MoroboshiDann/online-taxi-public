package org.moroboshidan.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JwtInterceptor())
                .addPathPatterns("/**") // 拦截的路径
                .excludePathPatterns("/authTestNoToken")
                .excludePathPatterns("/verification-code")
                .excludePathPatterns("/verification-code-check"); // 不拦截的路径
    }
}
