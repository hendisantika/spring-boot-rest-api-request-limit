package id.my.hendisantika.requestlimit.constants;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-rest-api-request-limit
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 21/01/25
 * Time: 14.00
 * To change this template use File | Settings | File Templates.
 */
public final class XHeader {

    public static final String X_API_KEY = "X-Api-Key";
    public static final String X_NO_API_KEY = "X-No-Api-Key";
    public static final String X_RATE_LIMIT_USER_NOT_ALLOWED = "X-Rate-Limit-User-Not-Allowed";
    public static final String X_RATE_LIMIT_IP_NOT_ALLOWED = "X-Rate-Limit-Ip-Not-Allowed";
    public static final String X_RATE_LIMIT_REMAINING = "X-Rate-Limit-Remaining";
    public static final String X_RATE_LIMIT_RETRY_AFTER_SECONDS = "X-Rate-Limit-Retry-After-Seconds";

    private XHeader() {
        throw new RuntimeException();
    }

}
