package com.ry.a05.component1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class Bean5 {

    private static final Logger log = LoggerFactory.getLogger(Bean5.class);

    public Bean5() {
        log.debug("我被 Spring 管理啦");
    }
}
