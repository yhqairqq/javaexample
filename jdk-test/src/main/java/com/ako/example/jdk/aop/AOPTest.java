package com.ako.example.jdk.aop;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by yanghuanqing@wdai.com on 07/12/2017.
 */
//@ContextConfiguration(locations = {"classpath:application.xml"})
//@ComponentScan(basePackages = {"com.wd.bigdata.javaexample.proxy.aop"})
//@Slf4j
//@RunWith(SpringJUnit4ClassRunner.class)
public class AOPTest {

//    @Autowired
//    private Actor obj;

    @Test
    public void test1(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("application.xml");
        Actor obj = (Actor)ctx.getBean("student",Actor.class);
        obj.work();
    }
}
