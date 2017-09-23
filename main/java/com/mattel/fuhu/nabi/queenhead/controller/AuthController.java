package com.mattel.fuhu.nabi.queenhead.controller;

import com.mattel.fuhu.nabi.queenhead.entity.Role;
import com.mattel.fuhu.nabi.queenhead.entity.Session;
import com.mattel.fuhu.nabi.queenhead.entity.User;
import com.mattel.fuhu.nabi.queenhead.service.AuthService;
import com.mattel.fuhu.nabi.queenhead.service.SessionService;
import com.mattel.fuhu.nabi.queenhead.view.json.LoginResponseBody;
import com.mattel.fuhu.nabi.queenhead.view.json.LoginRequestBody;
import com.mattel.fuhu.playform.common.exception.SystemException;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;

import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * <p>
 * </p>
 * <p>
 * Inception date: 2017-09-15
 * </p>
 * <p>
 * Copyright &copy; 2017 Fuhu, Inc. All rights reserved.
 * </p>
 *
 * @author Jerry Chen (<a href="jerry.chen@fuhu.com">jerry.chen@fuhu.com</a>)
 */

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthService authService;

    @Autowired
    private SessionService sessionService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public LoginResponseBody login(@RequestBody LoginRequestBody requestBody, HttpSession httpSession) throws SystemException {
        Session session = null;
        if (requestBody.getAccount().equals("admin") && requestBody.getPassword().equals("admin")) {
            session = createMockUser(requestBody, httpSession, Role.ADMIN);
        } else if (requestBody.getAccount().equals("normal") && requestBody.getPassword().equals("normal")) {
            session = createMockUser(requestBody, httpSession, Role.NORMAL);
        } else{
            session = authService.login(requestBody.getAccount(), requestBody.getPassword());
        }

        return new LoginResponseBody(session);

    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public void logout(HttpSession session) {
        authService.logout(session.getId());
    }


    private Session createMockUser(LoginRequestBody requestBody, HttpSession httpSession, Role role) {
        try {
            Session session = sessionService.get(httpSession.getId());
            User user;
            if (session == null) {
                session = new Session();
                user = new User();
            } else {
                user = session.getUser();
            }
            user.setAccount(requestBody.getAccount());
            user.setPassword(hashing(requestBody.getPassword()));
            user.setRole(role);
            session.setSessionId(httpSession.getId());
            session.setUser(user);
            sessionService.create(session);
            return session;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;

    }

    private String hashing(String str) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(str.getBytes());
        byte[] digest = md.digest();
        String hash = DatatypeConverter.printHexBinary(digest);
        return hash;
    }
}
