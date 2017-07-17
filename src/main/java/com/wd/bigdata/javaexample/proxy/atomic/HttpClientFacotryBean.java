//package com.wd.bigdata.javaexample.proxy.atomic;
//
///**
// * Created by yanghuanqing@wdai.com on 23/06/2017.
// */
///**
// * 在容器启动时注入connectionManager，然后初始化httpClient
// * 主要参数：
// * CONNECTION_TIMEOUT : 连接主机超时时间设置
// * SO_TIMEOUT :         读取主机数据超时时间设置
// * TIMEOUT :            获取连接超时时间
// */


/**
 * http://blog.csdn.net/reggergdsg/article/details/51835184
 */

///
//public class HttpClientFacotryBean implements FactoryBean,InitializingBean,DisposableBean {
//    private static final Logger logger = Logger.getLogger(HttpClientFacotryBean.class);
//    private DefaultHttpClient httpClient;
//    private ClientConnectionManager clientConnectionManager = null;
//    private Map map = null;
//    //设置httpClient超时参数
//    public void afterPropertiesSet() throws Exception {
//        if (null == clientConnectionManager) {
//            throw new BeanInitializationException("The connection manager must be set in " + this.getClass().getName() + "...");
//        }
//        HttpParams httpParams = new BasicHttpParams();
//        if (null != map) {
//            HttpConnectionParamBean httpCon
//
//

//

// nectionParamBean = new HttpConnectionParamBean(httpParams);
//            String connectionTimeout = (String) map.get(CoreConnectionPNames.CONNECTION_TIMEOUT);
//            if (null != connectionTimeout)
//                httpConnectionParamBean.setConnectionTimeout(Integer.parseInt(connectionTimeout));
//            String soTimeout = (String) map.get(CoreConnectionPNames.SO_TIMEOUT);
//            if (null != connectionTimeout)
//                httpConnectionParamBean.setSoTimeout(Integer.parseInt(soTimeout));
//            ConnManagerParamBean connManagerParamBean = new ConnManagerParamBean(httpParams);
//            String timeout = (String) map.get(ConnManagerPNames.TIMEOUT);
//            if (null != timeout)
//                connManagerParamBean.setTimeout(Long.parseLong(timeout));
//        }
//        this.httpClient = new DefaultHttpClient(clientConnectionManager, httpParams);
//        this.httpClient.addRequestInterceptor(new HttpRequestInterceptor() {
//            public void process(final HttpRequest request,final HttpContext context) throws HttpException,IOException {
//                AtomicInteger count = (AtomicInteger) context.getAttribute("count"); // 从HttpContext中获取计数器count
//                if (null == count) {
//                    count = new AtomicInteger(1); // 如果计数器为空，则初始化值为1
//                    context.setAttribute("count", count); // 放到context中
//                }
//                request.addHeader("Count", Integer.toString(count.getAndIncrement())); // 把计数器放到request请求中
//                if (logger.isDebugEnabled()) {
//                    logger.debug("\n=====这是第 " + count + " 次连接=====\n");
//                }
//            }
//        });
//    }
//    public void destroy() throws Exception {
//        if (null != params)
//            map.clear();
//        if (null != clientConnectionManager)
//            clientConnectionManager.closeExpiredConnections();
//    }
//    public ClientConnectionManager getConnectionManager() {
//        return clientConnectionManager;
//    }
//    public Map getParams() {
//        return map;
//    }
//    public void setConnectionManager(ClientConnectionManager clientConnectionManager) {
//        this.clientConnectionManager = clientConnectionManager;
//    }
//    public void setParams(Map map) {
//        this.map = map;
//    }
//    public Object getObject() throws Exception {
//        return this.httpClient;
//    }
//    public Class getObjectType() {
//        return HttpClient.class;
//    }
//    public boolean isSingleton() {
//        return false;
//    }
//}
