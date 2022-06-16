package com.ry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author ryang
 * @Description
 * @date 2022年05月22日 4:00 下午
 */
public class BeanFactoryTest {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // bean的定义
        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition(Config.class)
                .setScope("singleton").getBeanDefinition();
        // 将bean添加到容器中
        beanFactory.registerBeanDefinition("config", beanDefinition);

        // 给BeanFactory 添加常用的后处理器(包括 BeanFactory后处理器、Bean后处理器)
        // 这里只是将后处理器添加到容器中，想让后处理器发挥作用还需要下面的操作步骤
        AnnotationConfigUtils.registerAnnotationConfigProcessors(beanFactory);

        // BeanFactory 后处理器主要功能，补充了一些 bean 定义(通过解析@Bean等注解)
        beanFactory.getBeansOfType(BeanFactoryPostProcessor.class).values().forEach(beanFactoryPostProcessor -> {
            beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
        });

        // Bean 后处理器, 针对 bean 的生命周期的各个阶段提供扩展, 例如 @Autowired @Resource ...
        beanFactory.getBeansOfType(BeanPostProcessor.class).values().stream()
                // bean 后处理器会有排序的逻辑，默认使用 AnnotationAwareOrderComparator.INSTANCE即通过order来排
                .sorted(Objects.requireNonNull(beanFactory.getDependencyComparator()))
                .forEach(beanFactory::addBeanPostProcessor);

        // 将容器中的所有单例对象提前初始化，否则是懒加载，在通过beanFactory获取bean实例是才会初始化
        beanFactory.preInstantiateSingletons();

    }


    @Configuration
    static class Config {
        @Bean
        public Bean1 bean1() {
            return new Bean1();
        }

        @Bean
        public Bean2 bean2() {
            return new Bean2();
        }

        @Bean
        public Bean3 bean3() {
            return new Bean3();
        }

        @Bean
        public Bean4 bean4() {
            return new Bean4();
        }
    }

    interface Inter {

    }

    static class Bean3 implements Inter {

    }

    static class Bean4 implements Inter {

    }

    static class Bean1 {
        private static final Logger log = LoggerFactory.getLogger(Bean1.class);

        public Bean1() {
            log.debug("构造 Bean1()");
        }

        @Autowired
        private Bean2 bean2;

        public Bean2 getBean2() {
            return bean2;
        }

        @Autowired
        @Resource(name = "bean4")
        private Inter bean3;

        public Inter getInter() {
            return bean3;
        }
    }

    static class Bean2 {
        private static final Logger log = LoggerFactory.getLogger(Bean2.class);

        public Bean2() {
            log.debug("构造 Bean2()");
        }
    }
}
