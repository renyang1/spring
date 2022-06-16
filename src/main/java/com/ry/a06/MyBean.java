package com.ry.a06;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.annotation.PostConstruct;

/**
 * @author ryang
 * @Description
 * @date 2022年06月01日 7:46 下午
 */
@Slf4j
public class MyBean implements BeanNameAware, ApplicationContextAware, InitializingBean {
    @Override
    public void setBeanName(String name) {
        log.info("当前 Bean " + this + "名称是：" + name);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("当前bean " + this + " 初始化");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        log.info("当前bean " + this + " 容器是:" + applicationContext);
    }

    @Autowired
    public void aaa(ApplicationContext applicationContext) {
        log.info("当前bean " + this + " 使用@Autowired 容器是:" + applicationContext);
    }

    @PostConstruct
    public void init() {
        log.info("当前bean " + this + " 使用@PostConstruct 初始化");
    }
}
