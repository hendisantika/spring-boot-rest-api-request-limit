package id.my.hendisantika.requestlimit.interceptor;

import id.my.hendisantika.requestlimit.config.RateLimitConfig;
import id.my.hendisantika.requestlimit.constants.XHeader;
import id.my.hendisantika.requestlimit.service.PricingPlanService;
import id.my.hendisantika.requestlimit.service.UserService;
import io.github.bucket4j.ConsumptionProbe;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Objects;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-rest-api-request-limit
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 21/01/25
 * Time: 14.10
 * To change this template use File | Settings | File Templates.
 */
@Component
@RequiredArgsConstructor
public class ApiKeyRateLimitRequestInterceptor implements HandlerInterceptor {

    private final RateLimitConfig rateLimitConfig;
    private final PricingPlanService pricingPlanService;
    private final UserService userService;

    @Override
    public boolean preHandle(
            HttpServletRequest request, HttpServletResponse response, Object object) {
        final String apiKey = request.getHeader(XHeader.X_API_KEY);
        if (Objects.isNull(apiKey)) {
            RateLimitErrorHandler.handleNoApiKeyErrorForbiddenError(response);
            return false;
        }
        if (rateLimitConfig.getNotAllowedKeys().contains(apiKey)) {
            RateLimitErrorHandler.handleForbiddenError(
                    response, XHeader.X_RATE_LIMIT_USER_NOT_ALLOWED, apiKey);
            return false;
        }
        final RateLimitData rateLimitData = getRateLimitData(apiKey);
        if (Objects.isNull(rateLimitData)) {
            RateLimitErrorHandler.handleForbiddenError(
                    response, XHeader.X_RATE_LIMIT_USER_NOT_ALLOWED, apiKey);
            return false;
        }

        final ConsumptionProbe consumptionProbe =
                rateLimitData.getBucket().tryConsumeAndReturnRemaining(1);
        if (consumptionProbe.isConsumed()) {
            response.addHeader(
                    XHeader.X_RATE_LIMIT_REMAINING, String.valueOf(consumptionProbe.getRemainingTokens()));
            return true;
        }
        RateLimitErrorHandler.handleTooManyError(response, consumptionProbe);
        return false;
    }
}
