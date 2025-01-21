package id.my.hendisantika.requestlimit.interceptor;

import id.my.hendisantika.requestlimit.constants.XHeader;
import io.github.bucket4j.ConsumptionProbe;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-rest-api-request-limit
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 21/01/25
 * Time: 14.15
 * To change this template use File | Settings | File Templates.
 */
public final class RateLimitErrorHandler {

    private RateLimitErrorHandler() {
        throw new RuntimeException();
    }

    public static void handleTooManyError(
            HttpServletResponse response, ConsumptionProbe consumptionProbe) {
        final long waitForRefill = consumptionProbe.getNanosToWaitForRefill() / 1_000_000_000;
        response.addHeader(XHeader.X_RATE_LIMIT_RETRY_AFTER_SECONDS, String.valueOf(waitForRefill));

        handleResponseError(
                response,
                HttpStatus.TOO_MANY_REQUESTS,
                String.format(
                        "You have exhausted your API Request Quota, please try again in [%d] seconds.",
                        waitForRefill));
    }

    public static void handleForbiddenError(
            HttpServletResponse response, String header, String headerValue) {
        response.addHeader(header, headerValue);

        handleResponseError(response, HttpStatus.FORBIDDEN, "Forbidden");
    }
}
