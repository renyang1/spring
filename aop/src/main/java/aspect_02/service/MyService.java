package aspect_02.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class MyService {

    private static final Logger log = LoggerFactory.getLogger(MyService.class);

    final public void foo() {
        log.info("foo()");
        this.bar();
    }

    public void bar() {
        log.info("bar()");
    }
}
