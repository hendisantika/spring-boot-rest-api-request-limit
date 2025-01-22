package id.my.hendisantika.requestlimit.config;

import id.my.hendisantika.requestlimit.interceptor.ApiKeyRateLimitRequestInterceptor;
import id.my.hendisantika.requestlimit.interceptor.IpRateLimitRequestInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-rest-api-request-limit
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 21/01/25
 * Time: 14.07
 * To change this template use File | Settings | File Templates.
 */
@Configuration
@RequiredArgsConstructor
public class WebRtConfig implements WebMvcConfigurer {

    private final ApiKeyRateLimitRequestInterceptor apiKeyRateLimitRequestInterceptor;

    private final IpRateLimitRequestInterceptor ipRateLimitRequestInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry
                .addInterceptor(apiKeyRateLimitRequestInterceptor)
                .addPathPatterns("/api/v1/rt/api-key");
        registry.addInterceptor(ipRateLimitRequestInterceptor).addPathPatterns("/api/v1/rt/ip");
    }
}
