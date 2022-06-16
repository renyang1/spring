package aspect_02.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect // ⬅️注意此切面并未被 Spring 管理
public class MyAspect {

    private static final Logger log = LoggerFactory.getLogger(MyAspect.class);

    @Before("execution(* aspect_02.service.MyService.*())")
    public void before() {
        log.info("before()");
    }
}
