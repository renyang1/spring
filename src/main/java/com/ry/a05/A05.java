package com.ry.a05;

import org.springframework.context.support.GenericApplicationContext;

/**
 * @author ryang
 * @Description
 * @date 2022年05月27日 8:45 上午
 */
public class A05 {
    public static void main(String[] args) {
        GenericApplicationContext context = new GenericApplicationContext();
        context.registerBean("config", Config.class);

//        // @ComponentScan @Bean @Import @ImportResource
//        context.registerBean(ConfigurationClassPostProcessor.class);

//        // @Mapper
//        context.registerBean(MapperScannerConfigurer.class, bd ->
//                bd.getPropertyValues().add("basePackage", "com.ry.spring.com.ry.a05.mapper"));

//        context.registerBean(ComponentScanPostProcessor.class);
        context.registerBean(BeanPostProcessor.class);
        context.registerBean(MapperPostProcessor.class);
        context.refresh();
        for (String beanDefinitionName : context.getBeanDefinitionNames()) {
            System.out.println(beanDefinitionName);
        }
        context.close();

    }
}
