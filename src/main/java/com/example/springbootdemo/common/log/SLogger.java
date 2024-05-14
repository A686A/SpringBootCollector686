package com.example.springbootdemo.common.log;


import org.slf4j.Logger;
import org.springframework.context.MessageSource;

import java.io.UnsupportedEncodingException;
import java.util.Locale;

public class SLogger {
    private final Logger logger;
    private final MessageSource messageSource;

    public SLogger(Logger logger, MessageSource messageSource) {
        this.logger = logger;
        this.messageSource = messageSource;
    }

    public void info(String messageId) {
        if (logger.isInfoEnabled()) {
            logger.info(getMessage(messageId));
        }
    }

    public void info(String messageId, Object... args) {
        if (logger.isInfoEnabled()) {
            logger.info(getMessage(messageId, args));
        }
    }

    public void info(String messageId, Throwable e, Object... args) {
        if (logger.isInfoEnabled()) {
            logger.info(getMessage(messageId, args), e);
        }
    }


    public String getMessage(String messageId, Object... args) {
        return String.format("[%s]", messageId) + messageSource.getMessage(messageId, escapeArgs(args), Locale.JAPAN);
    }

    private Object[] escapeArgs(Object[] args) {
        Object[] altargs = new Object[args.length];
        for (int i = 0; i < args.length; i++) {
            Object o = args[i];
            if (o instanceof String) {
                try {
                    altargs[i] = LogUtils.doEscape(String.valueOf(o));
                } catch (UnsupportedEncodingException e) {
                    System.out.println("SLogger  UnsupportedEncodingException");
                }
            } else {
                altargs[i] = o;
            }
        }
        return altargs;
    }
}
