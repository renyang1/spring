package com.ry.a05;

import lombok.SneakyThrows;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * context.refresh()时会调用重写的方法
 *
 * @author ryang
 * @Description
 * @date 2022年05月30日 11:37 下午
 */
public class ComponentScanPostProcessor implements BeanDefinitionRegistryPostProcessor {
    @SneakyThrows
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanFactory) throws BeansException {
        // 获取Config类上的@ComponentScan注解
        // todo: 能否获取父类上的@ComponentScan注解
        ComponentScan componentScan = AnnotationUtils.findAnnotation(Config.class, ComponentScan.class);
        if (Objects.nonNull(componentScan)) {
            for (String basePackage : componentScan.basePackages()) {
                System.out.println(basePackage);
                // 拼接路径
                String path = "classpath*:" + basePackage.replace(".", "/") + "/**/*.class";
                System.out.println(path);

                CachingMetadataReaderFactory factory = new CachingMetadataReaderFactory();
                // 加载类路径下匹配的资源文件
                Resource[] resources = new PathMatchingResourcePatternResolver().getResources(path);
                AnnotationBeanNameGenerator generator = new AnnotationBeanNameGenerator();
                for (Resource resource : resources) {
                    MetadataReader metadataReader = factory.getMetadataReader(resource);
                    AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
                    if (annotationMetadata.hasAnnotation(Component.class.getName())
                            // 是否加了@Component派生注解
                            || annotationMetadata.hasMetaAnnotation(Component.class.getName())) {
                        // 创建 beanDefinition 实例对象
                        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition(
                                metadataReader.getClassMetadata().getClassName()).getBeanDefinition();
                        String beanName = generator.generateBeanName(beanDefinition, beanFactory);
                        // 将 bean 注册到工厂中
                        beanFactory.registerBeanDefinition(beanName, beanDefinition);
                    }
                }
            }
        }
    }

    @SneakyThrows
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }
}
