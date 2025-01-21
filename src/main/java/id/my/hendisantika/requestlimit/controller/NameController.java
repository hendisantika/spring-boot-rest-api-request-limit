package id.my.hendisantika.requestlimit.controller;

import id.my.hendisantika.requestlimit.service.NameService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-rest-api-request-limit
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 21/01/25
 * Time: 13.17
 * To change this template use File | Settings | File Templates.
 */
@Log4j2
@RestController
@RequestMapping("api/v1/names")
@AllArgsConstructor
class NameController {

    private final NameService nameService;

    @GetMapping
    public ResponseEntity<Mono<List<String>>> getAll() {
        log.info("====> {}", this.getClass().getName());
        return ResponseEntity.ok(nameService.getAllNames().collectList());
    }
}
