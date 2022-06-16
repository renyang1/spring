package com.ry.a07;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;

public class Bean1 implements InitializingBean {
    private static final Logger log = LoggerFactory.getLogger(Bean1.class);

    @PostConstruct
    public void init1() {
        log.info("初始化1");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("初始化2");
    }

    public void init3() {
        log.info("初始化3");
    }
}
