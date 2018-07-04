package com.ako.example.jdk.cglibproxy;

import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by Peter on 15/05/2017.
 */
public class CglibTest {

    private static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
            8,
            10,
            100,
            TimeUnit.MICROSECONDS,
            new LinkedBlockingDeque<Runnable>(),
            Executors.defaultThreadFactory(),
            new ThreadPoolExecutor.AbortPolicy()
    );

    @Test
    public void cglibTest(){

        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "/Users/YHQ/Downloads/1");
        UserServiceProxy cglib  = new UserServiceProxy();
        UserService userService = (UserService)cglib.getInstance(new UserService());
        userService.add("yanghuanqing");
    }
    @Test
    public void cglibTest2(){
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "/Users/YHQ/Downloads/2");

        long start = System.currentTimeMillis();
        final UserService userService = (UserService)new MethodInterceptor(){
            public Object getInstance(Object target){
                Enhancer enhancer = new Enhancer();
                enhancer.setSuperclass(target.getClass());
                enhancer.setCallback(this);
               return enhancer.create();
            }
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
//                System.out.println("before");

                proxy.invokeSuper(obj,args);

//                System.out.println("after");

                return null;
            }
        }.getInstance(new UserService());

        System.out.println("初始化："+(System.currentTimeMillis()-start));
        start = System.currentTimeMillis();

        List<Future> futures = new ArrayList<>();
        for(int i =0;i<5;i++){
            futures.add(threadPoolExecutor.submit(new Callable<Object>() {
                @Override
                public Object call() throws Exception {

                    for(int i=0;i<1000000000;i++){
                        userService.add("yanghaunqing");
                    }
                    return null;
                }
            }));
        }
        int i=0;
        while(true){

            if(futures.size() == 0)
                break;
            if(futures.get(i & (futures.size()-1)).isDone()){
                futures.remove(i & (futures.size()-1));
            }

            i++;
        }
        System.out.println("调用1000000000*5  并发时间:"+(System.currentTimeMillis() - start));
    }

    public static void main(String[] args){
        UserServiceProxy cglib  = new UserServiceProxy();
        UserService userService = (UserService)cglib.getInstance(new UserService());
        userService.add("yanghuanqing");
    }
}
