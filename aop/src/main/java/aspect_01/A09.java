package aspect_01;

import aspect_01.service.MyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ryang
 * @Description
 * @date 2022年06月07日 5:55 下午
 */
//@SpringBootApplication
public class A09 {
    private static final Logger log = LoggerFactory.getLogger(A09.class);
    public static void main(String[] args) {

//        ConfigurableApplicationContext context = SpringApplication.run(A09.class, args);
//        MyService myService = context.getBean(MyService.class);
//        log.info("myService class: {}", myService.getClass());
//        myService.f1();
//        context.close();

        new MyService().f1();
    }
}
