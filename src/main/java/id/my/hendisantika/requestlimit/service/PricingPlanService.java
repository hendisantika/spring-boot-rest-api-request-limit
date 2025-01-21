package id.my.hendisantika.requestlimit.service;

import id.my.hendisantika.requestlimit.config.PricingPlan;
import id.my.hendisantika.requestlimit.entity.UserPlan;
import io.github.bucket4j.Bucket;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-rest-api-request-limit
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 21/01/25
 * Time: 14.04
 * To change this template use File | Settings | File Templates.
 */
@Service
@RequiredArgsConstructor
public class PricingPlanService {
    private final Map<UserPlan, Bucket> PLAN_BUCKETS = new ConcurrentHashMap<>();
    private final Map<String, Bucket> IP_BUCKETS = new ConcurrentHashMap<>();

    public Bucket resolveBucketByUserPlan(UserPlan userPlan) {
        return PLAN_BUCKETS.computeIfAbsent(userPlan, this::newBucket);
    }

    public Bucket resolveBucketByIp(String ipAddress) {
        return IP_BUCKETS.computeIfAbsent(ipAddress, userPlan -> newBucket(UserPlan.FREE));
    }

    private Bucket newBucket(UserPlan userPlan) {
        final PricingPlan pricingPlan = PricingPlan.resolvePricingFromUserPlan(userPlan);
        return Bucket.builder().addLimit(pricingPlan.getLimit()).build();
    }
}
