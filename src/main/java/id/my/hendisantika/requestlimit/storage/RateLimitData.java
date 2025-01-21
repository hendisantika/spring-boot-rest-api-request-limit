package id.my.hendisantika.requestlimit.storage;

import id.my.hendisantika.requestlimit.entity.User;
import io.github.bucket4j.Bucket;
import lombok.Data;
import lombok.experimental.Accessors;

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
@Data
@Accessors(chain = true)
public class RateLimitData {

    private User user;
    private Bucket bucket;

}
