package com.wd.bigdata.javaexample.proxy.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by yanghuanqing@wdai.com on 05/07/2017.
 */

@Configuration
@ComponentScan("com.wd.bigdata.javaexample.proxy.spring")
public class Application {


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

        MessageContainer messageContainer = context.getBean(MessageContainer.class);

        messageContainer.printMessage();

    }
}
