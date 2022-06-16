package com.ry.a07;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * @author ryang
 * @Description
 * @date 2022年06月02日 8:07 下午
 */
@SpringBootApplication
public class A07 {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(A07.class, args);
        context.close();
    }

    @Bean(initMethod = "init3")
    public Bean1 bean1() {
        return new Bean1();
    }

    @Bean(destroyMethod = "destroy3")
    public Bean2 bean2() {
        return new Bean2();
    }
}
