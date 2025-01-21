package id.my.hendisantika.requestlimit.service;

import id.my.hendisantika.requestlimit.repository.FakeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-rest-api-request-limit
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 21/01/25
 * Time: 13.15
 * To change this template use File | Settings | File Templates.
 */
@Log4j2
@Service
@AllArgsConstructor
class NameService {
    private final FakeRepository fakeRepository;

    public Flux<String> getAllNames() {
        log.info("====> {}", this.getClass().getName());
        return Flux.fromIterable(FakeRepository.Actions.getAll())
                .map(FakeRepository.NameData::name);
    }
}
