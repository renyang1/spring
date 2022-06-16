package aspect_01.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author ryang
 * @Description
 * @date 2022年06月07日 5:42 下午
 */
@Service
public class MyService {
    private static final Logger log = LoggerFactory.getLogger(MyService.class);

    public void f1() {
        log.info("MyService f1()...");
    }
}
