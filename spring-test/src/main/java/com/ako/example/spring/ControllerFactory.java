package com.ako.example.spring;

/**
 * Created by yanghuanqing@wdai.com on 2018/7/11.
 */
public class ControllerFactory {
    public SelectController getController(String name){
        SelectController selectController = new SelectController();
        AutoWireCapbleBean.autowire(selectController);
        return selectController;
    }
}
