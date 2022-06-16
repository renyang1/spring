package com.ry.a04;

import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.annotation.InjectionMetadata;
import org.springframework.beans.factory.config.DependencyDescriptor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.ContextAnnotationAutowireCandidateResolver;
import org.springframework.core.MethodParameter;
import org.springframework.core.env.StandardEnvironment;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * AutowiredAnnotationBeanPostProcessor 运行分析
 * @author ryang
 * @Description
 * @date 2022年05月26日 5:13 下午
 */
public class DiginAutowired {
    public static void main(String[] args) throws Throwable {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        beanFactory.registerSingleton("bean2", new Bean2());
        beanFactory.registerSingleton("bean3", new Bean3());
        // @value
        beanFactory.setAutowireCandidateResolver(new ContextAnnotationAutowireCandidateResolver());
        // ${} 的解析器
        beanFactory.addEmbeddedValueResolver(new StandardEnvironment() :: resolvePlaceholders);


        AutowiredAnnotationBeanPostProcessor processor = new AutowiredAnnotationBeanPostProcessor();
        processor.setBeanFactory(beanFactory);

        Bean1 bean1 = new Bean1();
//        System.out.println(bean1);
//        // 执行依赖注入 @Autowired @Value
//        processor.postProcessProperties(null, bean1, "bean1");
//        System.out.println(bean1);

        Method findAutowiringMetadata = AutowiredAnnotationBeanPostProcessor.class.getDeclaredMethod(
                "findAutowiringMetadata", String.class, Class.class, PropertyValues.class);
        findAutowiringMetadata.setAccessible(true);
        // 1. 反射调用 processor 的私有方法 findAutowiringMetadata()，查找哪些属性、方法加了 @Autowired, 这称之为 InjectionMetadata
        InjectionMetadata injectionMetadata = (InjectionMetadata) findAutowiringMetadata.invoke(processor, "bean1", Bean1.class, null);
        System.out.println(injectionMetadata);

        // 2. 调用 InjectionMetadata 的inject()方法进行依赖注入，注入时按类型查找值
        injectionMetadata.inject(bean1, "bean1", null);
        System.out.println(bean1);

        // 3. 如何按类型查找
        Field bean3 = Bean1.class.getDeclaredField("bean3");
        DependencyDescriptor dd1 = new DependencyDescriptor(bean3, false);
        Object o = beanFactory.doResolveDependency(dd1, null, null, null);
        System.out.println(o);

        Method setBean2 = Bean1.class.getDeclaredMethod("setBean2", Bean2.class);
        DependencyDescriptor dd2 = new DependencyDescriptor(new MethodParameter(setBean2, 0), true);
        Object o1 = beanFactory.doResolveDependency(dd2, null, null, null);
        System.out.println(o1);

        Method setPort = Bean1.class.getDeclaredMethod("setPort", String.class);
        DependencyDescriptor dd3 = new DependencyDescriptor(new MethodParameter(setPort, 0), true);
        Object o2 = beanFactory.doResolveDependency(dd3, null, null, null);
        System.out.println(o2);
    }
}
