package id.my.hendisantika.requestlimit.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-rest-api-request-limit
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 21/01/25
 * Time: 13.59
 * To change this template use File | Settings | File Templates.
 */
@Data
@Accessors(chain = true)
public class RtResponse {

    private String key;
    private String value;
}
