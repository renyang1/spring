package com.ry.a06;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;

/**
 * @author ryang
 * @Description
 * @date 2022年06月02日 11:47 上午
 */
@Slf4j
public class MyConfig2 implements InitializingBean, ApplicationContextAware {

    @Bean
    public BeanFactoryPostProcessor processor2() {
        return beanFactory ->
                log.info("MyConfig2 执行 processor");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("MyConfig2 初始化。。。");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        log.info("MyConfig2 注入applicationContext={}", applicationContext);
    }
}
