package id.my.hendisantika.requestlimit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class SpringBootRestApiRequestLimitApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootRestApiRequestLimitApplication.class, args);
    }

}
