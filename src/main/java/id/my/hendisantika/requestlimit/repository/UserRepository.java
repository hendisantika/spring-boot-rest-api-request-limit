package id.my.hendisantika.requestlimit.repository;

import id.my.hendisantika.requestlimit.entity.User;
import id.my.hendisantika.requestlimit.entity.UserPlan;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-rest-api-request-limit
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 21/01/25
 * Time: 14.01
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class UserRepository {

    private static final Map<String, User> STORAGE = new ConcurrentHashMap<>();

    static {
        STORAGE.put(
                "00001",
                new User()
                        .setUsername("FreeUser")
                        .setApiKey("d0476978-free-4ad5-94e8-38ebb575f5c4")
                        .setUserPlan(UserPlan.FREE));
        STORAGE.put(
                "00002",
                new User()
                        .setUsername("BasicUser")
                        .setApiKey("d0476978-base-4ad5-94e8-38ebb575f5c5")
                        .setUserPlan(UserPlan.BASIC));
        STORAGE.put(
                "00003",
                new User()
                        .setUsername("ProUser")
                        .setApiKey("d0476978-prof-4ad5-94e8-38ebb575f5c6")
                        .setUserPlan(UserPlan.PRO));
    }

    public User getById(String id) {
        return STORAGE.values().stream()
                .filter(rtUser -> id.equalsIgnoreCase(rtUser.getId()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("There is no user with id: " + id));
    }
}
