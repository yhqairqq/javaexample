package com.ako.example.spring;

/**
 * Created by yanghuanqing@wdai.com on 2018/7/11.
 */
public class NodeTask extends Thread {

    private SelectController selectController;

    private ControllerFactory controllerFactory;

   public void init(){
       selectController = controllerFactory.getController("初始化");
   }


    public void setControllerFactory(ControllerFactory controllerFactory) {
        this.controllerFactory = controllerFactory;
    }
}
