package id.my.hendisantika.requestlimit.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-rest-api-request-limit
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 21/01/25
 * Time: 13.57
 * To change this template use File | Settings | File Templates.
 */
@Getter
@RequiredArgsConstructor
public enum UserPlan {
    FREE("FREE"),
    BASIC("BASIC"),
    PRO("PRO");

    private final String value;
}
