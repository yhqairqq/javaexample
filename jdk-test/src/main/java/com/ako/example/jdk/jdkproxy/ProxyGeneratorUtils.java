package com.ako.example.jdk.jdkproxy;

import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;

/**
 * Created by Peter on 15/05/2017.
 */
public class ProxyGeneratorUtils {

    /**
     * 把代理类的字节码写到硬盘上
     * @param path 保存路径
     */

    public static void writeProxyClass(String path){
        //第一种方法在刚才分析ProxyGenerator时就知道了
        //System.getProperties().put("sun.misc.ProxyGenerator.saveGenerator.saveGeneratedFiles",true);


        //第二种方法
        //获取代理类的字节码
        byte[] classFile = ProxyGenerator.generateProxyClass("$Proxy11",UserServiceImpl.class.getInterfaces());

        FileOutputStream out = null;
        try{
            out = new FileOutputStream(path);
            out.write(classFile);
            out.flush();
            out.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {

        }
    }
}
