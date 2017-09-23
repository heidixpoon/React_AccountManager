/*
 * Copyright (c) 2017 Mattel, Inc. All rights reserved.
 *
 * All information and code contained herein is the property of Mattel, Inc.
 *
 * Any unauthorized use, storage, duplication, and redistribution of this
 * material without written permission from Mattel, Inc. is strictly prohibited.
 */
package com.mattel.fuhu.nabi.queenhead.service.impl;

import com.mattel.fuhu.nabi.queenhead.entity.Role;
import com.mattel.fuhu.nabi.queenhead.entity.Session;
import com.mattel.fuhu.nabi.queenhead.entity.User;
import com.mattel.fuhu.nabi.queenhead.service.SessionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

/**
 * <p>
 * </p>
 * <p>
 * Inception date: 2017-09-13
 * </p>
 * <p>
 * Copyright &copy; 2017 Fuhu, Inc. All rights reserved.
 * </p>
 *
 * @author Ken Wang (<a href="mailto:ken.wang@fuhu.com">ken.wang@fuhu.com</a>)
 */

@Service
public class BasicSessionService implements SessionService{
    private static final Logger logger = LoggerFactory.getLogger(BasicSessionService.class);
    
    @Autowired
    private HttpSession httpSession;

    @Override
    public Session get(String sessionId) {
        if(!httpSession.isNew()){
            Session session = new Session();
            session.setSessionId(httpSession.getId());
            User user = new User();
            user.setAccount((String) httpSession.getAttribute("account"));
            user.setPassword((String)httpSession.getAttribute("password"));
            user.setRole((Role) httpSession.getAttribute("role"));
            session.setUser(user);
            return session;
        }

        return null;
    }

    @Override
    public void create(Session session) {
        User user = session.getUser();
        httpSession.setAttribute("account", user.getAccount());
        httpSession.setAttribute("password", user.getPassword());
        httpSession.setAttribute("role", user.getRole());
    }

    @Override
    public void delete(String sessionId) {
        httpSession.invalidate();
    }

}
