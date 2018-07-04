package com.ako.example.spring;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.ResourceLoader;

/**
 * Created by yanghuanqing@wdai.com on 2018/7/4.
 */
public class PropertyPlaceholderConfigurer extends org.springframework.beans.factory.config.PropertyPlaceholderConfigurer implements ResourceLoaderAware,InitializingBean {

    private static final String PLACEHOLDER_PREFIX = "${";
    private static final String PLACEHOLDER_SUBFIX = "}";
    private ResourceLoader resourceLoader;

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {

    }
}
