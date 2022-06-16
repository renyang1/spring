package aspect_01.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ryang
 * @Description
 * @date 2022年06月07日 5:43 下午
 */
@Aspect
public class MyAspect {

    private static final Logger log = LoggerFactory.getLogger(MyAspect.class);

    @Before("execution(* aspect_01.service.MyService.f1())")
    public void before() {
        log.info("MyAspect before()...");
    }
}
