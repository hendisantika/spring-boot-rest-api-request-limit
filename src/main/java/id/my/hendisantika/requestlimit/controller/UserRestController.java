package id.my.hendisantika.requestlimit.controller;

import id.my.hendisantika.requestlimit.entity.RtResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-rest-api-request-limit
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 21/01/25
 * Time: 14.19
 * To change this template use File | Settings | File Templates.
 */
@RestController
public class UserRestController {
    @GetMapping(value = "/api/v1/rt/api-key")
    public ResponseEntity<RtResponse> apiKeyResponse() {
        return ResponseEntity.ok().body(rtResponse("Api-Key-"));
    }
}
