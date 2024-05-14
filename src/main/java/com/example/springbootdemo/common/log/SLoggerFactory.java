package com.example.springbootdemo.common.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

public class SLoggerFactory {

    private static final ReloadableResourceBundleMessageSource messageSource =
            new ReloadableResourceBundleMessageSource();

    static {
        messageSource.addBasenames("classpath:messages");
        messageSource.setDefaultEncoding("UTF-8");
    }

    public static SLogger getLogger(String name) {
        Logger logger = LoggerFactory.getLogger(name);
        return new SLogger(logger, messageSource);
    }

    public static SLogger getLogger(Class<?> clazz) {
        Logger logger = LoggerFactory.getLogger(clazz);
        return new SLogger(logger, messageSource);
    }
}
