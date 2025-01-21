package id.my.hendisantika.requestlimit.repository;

import id.my.hendisantika.requestlimit.entity.User;
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
}
