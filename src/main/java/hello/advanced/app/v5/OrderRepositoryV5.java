package hello.advanced.app.v5;

import hello.advanced.app.trace.callback.TraceTemplate;
import hello.advanced.app.trace.logtrace.LogTrace;
import hello.advanced.app.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepositoryV5 {

    private final TraceTemplate template;

    @Autowired
    public OrderRepositoryV5(LogTrace logTrace) {
        template = new TraceTemplate(logTrace);
    }

    public void save(String itemId) throws InterruptedException {
        template.execute("OrderRepository.save()", () -> {
            if (itemId.equals("ex")) {
                throw new IllegalStateException("예외 발생");
            }
            sleep(1000);
            return null;
        });
    }

    private void sleep(int millis) throws InterruptedException {
        Thread.sleep(millis);
    }
}
