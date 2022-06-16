package com.ry.a06;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @author ryang
 * @Description
 * @date 2022年06月02日 11:47 上午
 */
@Configuration
@Slf4j
public class MyConfig1 {

    @Autowired
    private void setApplicationContext(ApplicationContext applicationContext) {
        log.info("MyConfig1: 注入 applicationContext={}", applicationContext);
    }

    @PostConstruct
    public void init() {
        log.info("MyConfig1: 初始化");
    }

    @Bean
    public BeanFactoryPostProcessor processor1() {
        return beanFactory ->
                log.info("MyConfig1: 执行 processor");
    }
}
