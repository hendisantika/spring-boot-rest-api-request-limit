package id.my.hendisantika.requestlimit.service;

import id.my.hendisantika.requestlimit.entity.User;
import id.my.hendisantika.requestlimit.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-rest-api-request-limit
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 21/01/25
 * Time: 14.03
 * To change this template use File | Settings | File Templates.
 */
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User getById(String id) {
        return userRepository.getById(id);
    }
}
