package com.ry.a04;

import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.context.properties.ConfigurationPropertiesBindingPostProcessor;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.context.annotation.ContextAnnotationAutowireCandidateResolver;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.env.StandardEnvironment;

/**
 * @author ryang
 * @Description
 * @date 2022年05月25日 2:56 下午
 */
public class A04 {
    public static void main(String[] args) {
        // 干净的容器
        GenericApplicationContext context = new GenericApplicationContext();

        // 注册bean
        context.registerBean("bean1", Bean1.class);
        context.registerBean("bean2", Bean2.class);
        context.registerBean("bean3", Bean3.class);
        context.registerBean("bean4", Bean4.class);

        DefaultListableBeanFactory factory = context.getDefaultListableBeanFactory();
        // @value值获取
        // todo:未能获取到值
        factory.setAutowireCandidateResolver(new ContextAnnotationAutowireCandidateResolver());
        factory.addEmbeddedValueResolver(new StandardEnvironment() :: resolvePlaceholders);

        // 解析@Autowired @Value
        context.registerBean(AutowiredAnnotationBeanPostProcessor.class);
        // 解析@Resource @PostConstruct @PreDestroy
        context.registerBean(CommonAnnotationBeanPostProcessor.class);

        // todo: 这行什么意思？
        ConfigurationPropertiesBindingPostProcessor.register(factory);

        // 初始化容器
        context.refresh();
        // 销毁容器
        context.close();
    }
}
