package com.ry.a05;

import lombok.SneakyThrows;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;

/**
 * @author ryang
 * @Description
 * @date 2022年06月01日 8:58 上午
 */
public class MapperPostProcessor implements BeanDefinitionRegistryPostProcessor {
    @SneakyThrows
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources("classpath*:com/ry/spring/com.ry.a05/mapper/**/*.class");
        AnnotationBeanNameGenerator beanNameGenerator = new AnnotationBeanNameGenerator();
        CachingMetadataReaderFactory readerFactory = new CachingMetadataReaderFactory();
        for (Resource resource : resources) {
            MetadataReader metadataReader = readerFactory.getMetadataReader(resource);
            // 类元数据
            ClassMetadata classMetadata = metadataReader.getClassMetadata();
            if (classMetadata.isInterface()) {
                AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition(MapperFactoryBean.class)
                        .addConstructorArgValue(classMetadata.getClassName())
                        .setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE)
                        .getBeanDefinition();
                // 为了生成 beanName
                AbstractBeanDefinition beanDefinition1 = BeanDefinitionBuilder.genericBeanDefinition(
                        classMetadata.getClassName()).getBeanDefinition();
                String beanName = beanNameGenerator.generateBeanName(beanDefinition1, registry);
                registry.registerBeanDefinition(beanName, beanDefinition);
            }
        }
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }
}
