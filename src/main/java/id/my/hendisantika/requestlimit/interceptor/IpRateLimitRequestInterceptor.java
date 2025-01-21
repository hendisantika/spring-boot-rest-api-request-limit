package id.my.hendisantika.requestlimit.interceptor;

import id.my.hendisantika.requestlimit.config.RateLimitConfig;
import id.my.hendisantika.requestlimit.constants.XHeader;
import id.my.hendisantika.requestlimit.service.PricingPlanService;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.ConsumptionProbe;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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

    @Override
    public boolean preHandle(
            HttpServletRequest request, HttpServletResponse response, Object object) {
        final String ip = request.getRemoteAddr();
        if (rateLimitConfig.getNotAllowedIps().contains(ip)) {
            RateLimitErrorHandler.handleForbiddenError(response, XHeader.X_RATE_LIMIT_IP_NOT_ALLOWED, ip);
            return false;
        }

        final Bucket tokenBucket = pricingPlanService.resolveBucketByIp(ip);
        final ConsumptionProbe consumptionProbe = tokenBucket.tryConsumeAndReturnRemaining(1);
        if (consumptionProbe.isConsumed()) {
            response.addHeader(
                    XHeader.X_RATE_LIMIT_REMAINING, String.valueOf(consumptionProbe.getRemainingTokens()));
            return true;
        }
        RateLimitErrorHandler.handleTooManyError(response, consumptionProbe);
        return false;
    }
}
