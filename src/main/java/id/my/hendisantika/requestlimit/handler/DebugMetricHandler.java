package id.my.hendisantika.requestlimit.handler;

import com.giffing.bucket4j.spring.boot.starter.context.metrics.MetricHandler;
import com.giffing.bucket4j.spring.boot.starter.context.metrics.MetricTagResult;
import com.giffing.bucket4j.spring.boot.starter.context.metrics.MetricType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-rest-api-request-limit
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 21/01/25
 * Time: 13.18
 * To change this template use File | Settings | File Templates.
 */
//Only for print the metric as log
@Component
@Slf4j
class DebugMetricHandler implements MetricHandler {

    /*
     These are the Metric types available:
      CONSUMED_COUNTER
      REJECTED_COUNTER
      PARKED_COUNTER
      INTERRUPTED_COUNTER
      DELAYED_COUNTER
     */
    @Override
    public void handle(MetricType type, String name, long tokens, List<MetricTagResult> tags) {
        log.info("type: {}; name: {}; tags: {}", type, name, tags.stream()
                .map(mtr -> mtr.getKey() + ":" + mtr.getValue())
                .collect(Collectors.joining(",")));
    }
}
