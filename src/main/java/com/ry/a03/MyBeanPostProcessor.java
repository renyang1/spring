package com.ry.a03;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author ryang
 * @Description
 * @date 2022年05月23日 7:32 下午
 */
@Component
@Slf4j
public class MyBeanPostProcessor implements InstantiationAwareBeanPostProcessor, DestructionAwareBeanPostProcessor {

    private static final String BEAN_NAME = "lifeCycleBean";

    @Override
    public void postProcessBeforeDestruction(Object bean, String beanName) throws BeansException {
        if (Objects.equals(beanName, BEAN_NAME)) {
            log.info("<<<<<< 销毁之前执行, 如 @PreDestroy");
        }
    }

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        if (Objects.equals(beanName, BEAN_NAME)) {
            log.info("<<<<<< 实例化之前执行, 这里返回的对象会替换掉原本的 bean");
        }
        return null;
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        if (beanName.equals(BEAN_NAME)) {
            log.info("<<<<<< 实例化之后执行, 这里如果返回 false 会跳过依赖注入阶段");
//            return false;
        }
        return true;
    }

    @Override
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        if (beanName.equals(BEAN_NAME))
            log.info("<<<<<< 依赖注入前执行, 如 @Autowired、@Value、@Resource");
        return pvs;
    }


    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.equals(BEAN_NAME))
            log.info("<<<<<< 初始化之前执行, 这里返回的对象会替换掉原本的 bean, 如 @PostConstruct、@ConfigurationProperties");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.equals(BEAN_NAME))
            log.info("<<<<<< 初始化之后执行, 这里返回的对象会替换掉原本的 bean, 如代理增强");
        return bean;
    }
}
