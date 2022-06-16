package com.ry.a06;

import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.context.annotation.ConfigurationClassPostProcessor;
import org.springframework.context.support.GenericApplicationContext;

/**
 * @author ryang
 * @Description
 * @date 2022年06月01日 7:45 下午
 */
public class A06 {
    public static void main(String[] args) {
        GenericApplicationContext context = new GenericApplicationContext();

        context.registerBean("myConfig1", MyConfig1.class);
        context.registerBean("myConfig2", MyConfig2.class);
        // @Autowired 注解
        context.registerBean(AutowiredAnnotationBeanPostProcessor.class);
        // 解析 @Resource、@PostConstruct、@PreDestroy
        context.registerBean(CommonAnnotationBeanPostProcessor.class);
        //  @Bean
        context.registerBean(ConfigurationClassPostProcessor.class);
        context.refresh();

        for (String beanDefinitionName : context.getBeanDefinitionNames()) {
            System.out.println(beanDefinitionName);
        }
        context.close();
    }
}
