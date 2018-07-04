package com.ako.example.jdk.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Created by yanghuanqing@wdai.com on 07/12/2017.
 */
@Component
@Aspect
public class DelegateHandler {
    @Around(value = "execution(* com.wd.bigdata.javaexample.proxy.aop.Actor.work())")
    public Object processWalk(ProceedingJoinPoint joinPoint) throws Throwable {
        before("work");
        Object result =  joinPoint.proceed();
        after("work");
        return result;
    }


    @Around(value = "execution(* com.wd.bigdata.javaexample.proxy.aop.Actor.jump())")
    public Object processJump(ProceedingJoinPoint joinPoint) throws Throwable {
        before("jump2222");
        Object result =  joinPoint.proceed();
        after("jump2222");
        return result;
    }


    @Around(value = "execution(* com.wd.bigdata.javaexample.proxy.aop.Teacher.jump())")
    public Object processTeacherJump(ProceedingJoinPoint joinPoint) throws Throwable {
        before("Teacher jump 1");
        Object result =  joinPoint.proceed();
        after("Teacher jump 2");
        return result;
    }

    private void before(String params) {
        System.out.println("before:" + params);
    }

    private void after(String params) {
        System.out.println("after:" + params);
    }
}
