package id.my.hendisantika.requestlimit.interceptor;

import id.my.hendisantika.requestlimit.config.RateLimitConfig;
import id.my.hendisantika.requestlimit.service.PricingPlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-rest-api-request-limit
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 21/01/25
 * Time: 14.14
 * To change this template use File | Settings | File Templates.
 */
@Component
@RequiredArgsConstructor
public class IpRateLimitRequestInterceptor implements HandlerInterceptor {

    private final RateLimitConfig rateLimitConfig;
    private final PricingPlanService pricingPlanService;

}
