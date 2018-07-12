package com.ako.example.dubbo.nio;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.alibaba.dubbo.rpc.Exporter;
import com.alibaba.dubbo.rpc.ProxyFactory;
import com.alibaba.dubbo.rpc.protocol.dubbo.DubboProtocol;

import java.text.MessageFormat;

/**
 * Created by yanghuanqing@wdai.com on 2018/7/12.
 */
public class DubboCommunicationEndpoint extends AbstractCommunicationEndpoint {


    private static final String DUBBO_SERVICE_URL = "dubbo://127.0.0.1:{0}/endpoint?server=netty&codec=dubbo&serialization=hessian2&heartbeat=5000&iothreads=4&threads=50&connections=30&payload={1}";

    private DubboProtocol protocol = DubboProtocol.getDubboProtocol();

    private ProxyFactory proxyFactory = ExtensionLoader.getExtensionLoader(ProxyFactory.class).getExtension("javassist");

    private Exporter<CommunicationEndpoint> exporter = null;

    private int port = 2028;
    private int payload = Constants.DEFAULT_PAYLOAD;

    public DubboCommunicationEndpoint(int port) {
        this.port = port;
    }

    public DubboCommunicationEndpoint() {
    }

    @Override
    public void initial() {
        // 构造对应的url， String.valueOf() 为避免数字包含千位符
        String url = MessageFormat.format(DUBBO_SERVICE_URL, String.valueOf(port), String.valueOf(payload));
        exporter = protocol.export(proxyFactory.getInvoker(this, CommunicationEndpoint.class, URL.valueOf(url)));
    }

    @Override
    public void destory() {
        exporter.unexport();
    }
}
