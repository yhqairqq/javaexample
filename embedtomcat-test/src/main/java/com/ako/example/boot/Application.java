package com.ako.example.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yanghuanqing@wdai.com on 2018/8/6.
 */
@RestController
@SpringBootApplication(scanBasePackageClasses ={Application.class} )
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
//mapper 接口类扫描包配置
public class Application extends SpringBootServletInitializer {//启动类继承SpringBootServletInitializer

    /*********
     * index入口
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    Object index ( ) {

        return "<h1>web-api project @ zjcjava 2018</h1>";
    }


    //启动main方法
    public static void main(String[] args)  {//throws Exception
        SpringApplication.run(Application.class, args);
    }



}