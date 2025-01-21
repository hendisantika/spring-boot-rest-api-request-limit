package id.my.hendisantika.requestlimit;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@EnableCaching
@SpringBootApplication
public class SpringBootRestApiRequestLimitApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootRestApiRequestLimitApplication.class, args);
    }

}

// INIT
@Log4j2
@Component
class Init {
    @EventListener(ApplicationReadyEvent.class)
    public void ready() {
        log.info("====> {}", this.getClass().getName());
    }
}
