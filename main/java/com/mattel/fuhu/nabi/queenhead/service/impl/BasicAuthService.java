/*
 * Copyright (c) 2017 Mattel, Inc. All rights reserved.
 *
 * All information and code contained herein is the property of Mattel, Inc.
 *
 * Any unauthorized use, storage, duplication, and redistribution of this
 * material without written permission from Mattel, Inc. is strictly prohibited.
 */
package com.mattel.fuhu.nabi.queenhead.service.impl;

import com.mattel.fuhu.nabi.queenhead.common.exception.auth.LoginInfoErrorException;
import com.mattel.fuhu.nabi.queenhead.common.exception.auth.LoginInfoLackingException;
import com.mattel.fuhu.nabi.queenhead.common.exception.user.AccountNotExistedException;
import com.mattel.fuhu.nabi.queenhead.entity.Role;
import com.mattel.fuhu.nabi.queenhead.service.SessionService;
import com.mattel.fuhu.nabi.queenhead.service.UserService;
import com.mattel.fuhu.playform.common.exception.SystemException;
import com.mattel.fuhu.playform.common.util.StringUtil;
import org.omg.PortableInterceptor.USER_EXCEPTION;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mattel.fuhu.nabi.queenhead.entity.Session;
import com.mattel.fuhu.nabi.queenhead.entity.User;
import com.mattel.fuhu.nabi.queenhead.service.AuthService;

import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
public class BasicAuthService implements AuthService {
    private static final Logger logger = LoggerFactory.getLogger(BasicAuthService.class);

    @Autowired
    private SessionService sessionService;

    @Autowired
    private UserService userService;

    @Autowired
    private HttpSession httpSession;

    @Override
    public Session login(String account, String password) throws SystemException {
        if (StringUtil.isNullOrEmpty(account) || StringUtil.isNullOrEmpty(password)) {
            throw new LoginInfoLackingException();
        }

        // Check user
        User user = userService.getUser(account);
        if(!checkUser(account, password, user)){
            throw new LoginInfoErrorException();
        }

        // validate session
        Session session = sessionService.get(httpSession.getId());
        if(session == null){
            session = new Session();
            session.setUser(user);
            session.setSessionId(httpSession.getId());
            sessionService.create(session);
        }

        return session;
    }

    private boolean checkUser(String account, String password, User user) {
        try {
            if (!user.getAccount().equals(account) || !user.getPassword().equals(hashing(password))) {
                return false;
            }
            return true;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void logout(String sessionId) {
        sessionService.delete(sessionId);
    }

    @Override
    public User validate(String sessionId) {
        Session session = sessionService.get(sessionId);
        User user = null;
        if(session != null){
            user = session.getUser();
        }
        return user;
    }

    private String hashing(String str) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(str.getBytes());
        byte[] digest = md.digest();
        String hash = DatatypeConverter.printHexBinary(digest);
        return hash;
    }

}
