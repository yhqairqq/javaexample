package com.ako.example.dubbo.nio;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by yanghuanqing@wdai.com on 2018/7/12.
 */
public class ClientLauncher {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:client.xml");

        DubboCommunicationConnectionFactory connectionFactory = (DubboCommunicationConnectionFactory) context.getBean("connectionFactory");

        CommunicationParam communicationParam = new CommunicationParam();
        communicationParam.setPort(2028);
        communicationParam.setIp("127.0.0.1");
        CommunicationConnection connection =  connectionFactory.createConnection(communicationParam);
        connection.call(new HeartEvent());
    }
}
