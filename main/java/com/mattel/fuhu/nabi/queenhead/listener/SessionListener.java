package com.mattel.fuhu.nabi.queenhead.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * <p>
 * </p>
 * <p>
 * Inception date: 2017-09-19
 * </p>
 * <p>
 * Copyright &copy; 2017 Fuhu, Inc. All rights reserved.
 * </p>
 *
 * @author Jerry Chen (<a href="jerry.chen@fuhu.com">jerry.chen@fuhu.com</a>)
 */

@WebListener
public class SessionListener implements HttpSessionListener{
    private static final Logger logger = LoggerFactory.getLogger(SessionListener.class);

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        logger.debug("sessionCreated");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        logger.debug("sessionDestroyed");
    }
}
