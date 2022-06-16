package com.ry.a07;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;

import javax.annotation.PreDestroy;

public class Bean2 implements DisposableBean {
    private static final Logger log = LoggerFactory.getLogger(Bean2.class);

    @PreDestroy
    public void destroy1() {
        log.info("销毁1");
    }

    @Override
    public void destroy() throws Exception {
        log.info("销毁2");
    }

    public void destroy3() {
        log.info("销毁3");
    }
}
