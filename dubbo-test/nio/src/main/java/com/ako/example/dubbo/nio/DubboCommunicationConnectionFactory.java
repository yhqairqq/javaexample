package com.ako.example.dubbo.nio;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.alibaba.dubbo.rpc.ProxyFactory;
import com.alibaba.dubbo.rpc.protocol.dubbo.DubboProtocol;
import com.google.common.base.Function;
import com.google.common.collect.OtterMigrateMap;

import java.text.MessageFormat;
import java.util.Map;

/**
 * Created by yanghuanqing@wdai.com on 2018/7/12.
 */
public class DubboCommunicationConnectionFactory implements CommunicationConnectionFactory {

    private final String DUBBO_SERVICE_URL = "dubbo://{0}:{1}/endpoint?client=netty&codec=dubbo&serialization=hessian2&lazy=true&iothreads=4&threads=50&connections=30&acceptEvent.timeout=5000&payload={2}";
    private DubboProtocol protocol = DubboProtocol.getDubboProtocol();
    private ProxyFactory proxyFactory  = ExtensionLoader.getExtensionLoader(ProxyFactory.class).getExtension("javassist");

    private  int payload  = Constants.DEFAULT_PAYLOAD;

    private Map<String,CommunicationEndpoint> connections = null;

    public DubboCommunicationConnectionFactory() {
        connections = OtterMigrateMap.makeComputingMap(new Function<String, CommunicationEndpoint>() {

            public CommunicationEndpoint apply(String serviceUrl) {
                return proxyFactory.getProxy(protocol.refer(CommunicationEndpoint.class, URL.valueOf(serviceUrl)));
            }
        });
    }

    public CommunicationConnection createConnection(CommunicationParam params) {
        if (params == null) {
            throw new IllegalArgumentException("param is null!");
        }
        // 构造对应的url， String.valueOf() 为避免数字包含千位符
        String serviceUrl = MessageFormat.format(DUBBO_SERVICE_URL, params.getIp(), String.valueOf(params.getPort()), String.valueOf(payload));
        CommunicationEndpoint endpoint = connections.get(serviceUrl);
        return new DubboCommunicationConnection(params, endpoint);

    }

    @Override
    public void releaseConnection(CommunicationConnection connection) {

    }
}
