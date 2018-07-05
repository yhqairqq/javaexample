package com.ako.example.spring;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yanghuanqing@wdai.com on 2018/7/4.
 */
public class PropertyPlaceholderConfigurer extends org.springframework.beans.factory.config.PropertyPlaceholderConfigurer implements ResourceLoaderAware, InitializingBean {

    private static final String PLACEHOLDER_PREFIX = "${";
    private static final String PLACEHOLDER_SUBFIX = "}";
    private ResourceLoader resourceLoader;
    private String[] locationNames;

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(resourceLoader, "no resourceLoader");
        if (locationNames != null) {
            for (int i = 0; i < locationNames.length; i++) {
                locationNames[i] = resolveSystemProperty(locationNames[i]);
            }
        }

        if (locationNames != null) {
            List<Resource> resources = new ArrayList<>(locationNames.length);
            for (String location : locationNames) {
                location = trimToNull(location);
                if(location !=null){
                    resources.add(resourceLoader.getResource(location));
                }
            }
            super.setLocations(resources.toArray(new Resource[resources.size()]));
        }
    }

    public PropertyPlaceholderConfigurer() {
        setIgnoreUnresolvablePlaceholders(true);
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public void setLocationNames(String[] locationNames) {
        this.locationNames = locationNames;
    }

    private String resolveSystemPropertyPlaceholders(String text) {
        StringBuilder builder = new StringBuilder(text);
        for (int startIndex = builder.indexOf(PLACEHOLDER_PREFIX); startIndex >= 0; ) {
            int endIndex = builder.indexOf(PLACEHOLDER_SUBFIX, startIndex + PLACEHOLDER_PREFIX.length());
            if (endIndex != -1) {
                String placeholder = builder.substring(startIndex + PLACEHOLDER_PREFIX.length(), endIndex);
                int nextIndex = endIndex + PLACEHOLDER_SUBFIX.length();
                try {
                    String value = resolveSystemPropertyPlaceholder(placeholder);
                    if (value != null) {
                        builder.replace(startIndex, endIndex + PLACEHOLDER_SUBFIX.length(), value);
                        nextIndex = startIndex + value.length();
                    } else {
                        System.err.println(
                                "Could not resolve placeholder '"
                                        + placeholder
                                        + "' in ["
                                        + text
                                        + "] as system property: neither system property nor environment variable found"
                        );
                    }
                } catch (Throwable e) {

                }
                startIndex = builder.indexOf(PLACEHOLDER_PREFIX, nextIndex);
            } else {
                startIndex = -1;
            }
        }
        return builder.toString();
    }


    private String resolveSystemPropertyPlaceholder(String placeholder) {
        DefualtPlaceholder dp = new DefualtPlaceholder(placeholder);
        String value = System.getProperty(dp.placeholder);
        if (value == null) {
            value = System.getenv(dp.placeholder);
        }
        if (value == null) {
            value = dp.defaultValue;
        }
        return value;
    }

    private static class DefualtPlaceholder {
        private final String defaultValue;
        private final String placeholder;

        public DefualtPlaceholder(String placeholder) {
            int commaIndex = placeholder.indexOf(":");
            String defaultValue = null;
            if (commaIndex >= 0) {
                defaultValue = trimToEmpty(placeholder.substring(commaIndex + 1));
                placeholder = trimToEmpty(placeholder.substring(0, commaIndex));
            }
            this.defaultValue = defaultValue;
            this.placeholder = placeholder;
        }
    }


    private static String trimToEmpty(String str) {
        if (str == null) {
            return "";
        }
        return str.trim();
    }

    public static String trimToNull(String str) {
        if (str == null) {
            return null;
        }
        String result = str.trim();
        if (result == null || result.length() == 0) {
            return null;
        }
        return result;
    }
}
