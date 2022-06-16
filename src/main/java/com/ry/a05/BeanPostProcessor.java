package com.ry.a05;

import lombok.SneakyThrows;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.type.MethodMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;

import java.util.Map;
import java.util.Set;

/**
 * @author ryang
 * @Description
 * @date 2022年05月31日 9:25 上午
 */
public class BeanPostProcessor implements BeanDefinitionRegistryPostProcessor {
    @SneakyThrows
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        CachingMetadataReaderFactory factory = new CachingMetadataReaderFactory();
        MetadataReader metadataReader = factory.getMetadataReader(new ClassPathResource("com/ry/spring/com.ry.a05/Config.class"));
        // 通过注解元数据获取直接或间接标注的注解信息
        Set<MethodMetadata> methods = metadataReader.getAnnotationMetadata().getAnnotatedMethods(Bean.class.getName());
        for (MethodMetadata method : methods) {
            System.out.println(method);
            // 获取方法上 @Bean 注解所有属性
            Map<String, Object> annotationAttributes = method.getAnnotationAttributes(Bean.class.getName());
            String initMethod = annotationAttributes.get("initMethod").toString();

            BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition();
            // 实质是调用工厂中 config 对象实例 method.getMethodName()对应的方法名，从而创建bean对象
            builder.setFactoryMethodOnBean(method.getMethodName(), "config");
            builder.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_CONSTRUCTOR);

            if (initMethod.length() > 0) {
                // 设置初始化方法
                builder.setInitMethodName(initMethod);
            }
            // 创建 beanDefinition 对象
            AbstractBeanDefinition beanDefinition = builder.getBeanDefinition();
            // 注册 bean 到容器中
            registry.registerBeanDefinition(method.getMethodName(), beanDefinition);
        }

    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }
}
