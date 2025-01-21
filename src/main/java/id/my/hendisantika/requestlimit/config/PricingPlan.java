package id.my.hendisantika.requestlimit.config;

import id.my.hendisantika.requestlimit.entity.UserPlan;
import io.github.bucket4j.Bandwidth;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.Duration;

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
@Getter
@RequiredArgsConstructor
public enum PricingPlan {
    FREE(20),
    BASIC(40),
    PROFESSIONAL(100);

    private final int bucketCapacity;

    public static PricingPlan resolvePricingFromUserPlan(UserPlan userPlan) {
        if (UserPlan.BASIC.equals(userPlan)) {
            return BASIC;
        }
        if (UserPlan.PRO.equals(userPlan)) {
            return PROFESSIONAL;
        }
        return FREE;
    }

    public Bandwidth getLimit() {
        return Bandwidth.builder()
                .capacity(bucketCapacity)
                .refillIntervally(bucketCapacity, Duration.ofHours(1))
                .build();
    }
}
