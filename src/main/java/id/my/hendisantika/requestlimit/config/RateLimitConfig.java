package id.my.hendisantika.requestlimit.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-rest-api-request-limit
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 21/01/25
 * Time: 14.06
 * To change this template use File | Settings | File Templates.
 */
@Configuration
@Data
public class RateLimitConfig {

    @Value("${id.my.hendisantika.rate.limit.not.allowed.keys}")
    private List<String> notAllowedKeys;
    @Value("${id.my.hendisantika.rate.limit.not.allowed.ips}")
    private List<String> notAllowedIps;
}
