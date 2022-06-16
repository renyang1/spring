package com.ry.a08;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author ryang
 * @Description
 * @date 2022年06月06日 11:55 下午
 */
@SpringBootApplication
@Slf4j
public class A08 {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(A08.class);

        log.info("-----------------------------------");
        E e = context.getBean(E.class);
        log.info("{}",e.getF1().getClass());
        log.info("{}",e.getF1());
        log.info("{}",e.getF1());

        log.info("{}",e.getF2().getClass());
        log.info("{}",e.getF2());
        log.info("{}",e.getF2());

        log.info("{}",e.getF3().getClass());
        log.info("{}",e.getF3());
        log.info("{}",e.getF3());

        log.info("{}",e.getF4().getClass());
        log.info("{}",e.getF4());
        log.info("{}",e.getF4());

        context.close();
    }
}
