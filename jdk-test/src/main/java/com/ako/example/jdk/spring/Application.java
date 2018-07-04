package com.ako.example.jdk.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;

/**
 * Created by yanghuanqing@wdai.com on 05/07/2017.
 */

@Configuration
@ComponentScan("com.wd.bigdata.javaexample.proxy.spring")
@PropertySource("classpath:config.properties")
public class Application {

//    @Bean
//    @Order(-100)
//    @Primary
//    public SpringMvcApiExtractor getSpringMvcApiExtractor(){
//        SpringMvcApiExtractor springMvcApiExtractor=new SpringMvcApiExtractor();
//        SimpleStylePersistService simpleStylePersistService =new SimpleStylePersistService();
//        simpleStylePersistService.setStorePath("api.json");
//        springMvcApiExtractor.setPersistService(simpleStylePersistService);
//        return springMvcApiExtractor;
//    }

    @Bean
    public MessageContainer getMessageContainer(){
        return new MessageContainer() {
            @Override
            public void printMessage() {
                System.out.println("this is message Containner");
            }
        };
    }


    public static void main(String[] args) {
        ApplicationContext context
                = new AnnotationConfigApplicationContext(Application.class);

//        MessageContainer messageContainer = context.getBean(MessageContainer.class);
//
//        messageContainer.printMessage();

    }
}
