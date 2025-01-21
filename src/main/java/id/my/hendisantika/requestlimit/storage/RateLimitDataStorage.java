package id.my.hendisantika.requestlimit.storage;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-rest-api-request-limit
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 21/01/25
 * Time: 14.17
 * To change this template use File | Settings | File Templates.
 */
public final class RateLimitDataStorage {

    private static final Map<String, RateLimitData> STORAGE = new ConcurrentHashMap<>();

    private RateLimitDataStorage() {
        throw new RuntimeException();
    }

    public static RateLimitData addRateLimitData(String apiKey, RateLimitData rateLimitData) {
        return STORAGE.putIfAbsent(apiKey, rateLimitData);
    }

    public static RateLimitData getRateLimitData(String apiKey) {
        return STORAGE.get(apiKey);
    }

}
