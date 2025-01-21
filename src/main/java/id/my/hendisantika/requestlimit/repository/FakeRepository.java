package id.my.hendisantika.requestlimit.repository;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-rest-api-request-limit
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 21/01/25
 * Time: 13.04
 * To change this template use File | Settings | File Templates.
 */
@Log4j2
@Component
public class FakeRepository {
    private static List<NameData> nameDataStream = new ArrayList<>();

    public record NameData(String name) {
    }

    static {
        nameDataStream.add(new NameData("Yuji"));
        nameDataStream.add(new NameData("Gojo"));
        nameDataStream.add(new NameData("Geto"));
        log.info("====> data loaded {}", nameDataStream.stream().map(NameData::name).collect(Collectors.joining(",")));
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Actions {
        public static List<NameData> getAll() {
            return nameDataStream;
        }

        public static void create(String name) {
            nameDataStream = Stream.concat(nameDataStream.stream(), Stream.of(new NameData(name))).toList();
        }
    }
}
