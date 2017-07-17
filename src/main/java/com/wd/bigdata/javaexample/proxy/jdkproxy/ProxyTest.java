package com.wd.bigdata.javaexample.proxy.jdkproxy;

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
public class ProxyTest {

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
    public void testProxy(){

        UserService userService = new UserServiceImpl();


        CustomInvacationHandler invacationHandler = new CustomInvacationHandler(userService);


        UserService proxy = (UserService)invacationHandler.getPorxy();


        proxy.add("yanghuanqing");

    }
    @Test
    public void test2(){

        long start = System.currentTimeMillis();
        UserService userService = new UserServiceImpl();


        CustomInvacationHandler invacationHandler = new CustomInvacationHandler(userService);


        UserService proxy = (UserService)invacationHandler.getPorxy();

        System.out.println("初始化："+(System.currentTimeMillis()-start));
        start = System.currentTimeMillis();

        List<Future> futures = new ArrayList<>();
        for(int i =0;i<5;i++){
            futures.add(threadPoolExecutor.submit(new Callable<Object>() {
                @Override
                public Object call() throws Exception {

                    for(int i=0;i<1000000000;i++){
                        proxy.add("yanghuanqing");
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

    @Test
    public void testGeneratorProxyClass(){
        ProxyGeneratorUtils.writeProxyClass("/Users/YHQ/Downloads/$Proxy11.class");
    }
}
