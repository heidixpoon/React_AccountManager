/*
 * Copyright (c) 2017 Mattel, Inc. All rights reserved.
 *
 * All information and code contained herein is the property of Mattel, Inc.
 *
 * Any unauthorized use, storage, duplication, and redistribution of this
 * material without written permission from Mattel, Inc. is strictly prohibited.
 */
package com.mattel.fuhu.nabi.queenhead.service.impl;

import com.mattel.fuhu.nabi.queenhead.common.exception.user.AccountNotExistedException;
import com.mattel.fuhu.nabi.queenhead.entity.User;
import com.mattel.fuhu.nabi.queenhead.persistence.QueenheadDaoFactory;
import com.mattel.fuhu.nabi.queenhead.persistence.UserDao;
import com.mattel.fuhu.nabi.queenhead.service.UserService;
import com.mattel.fuhu.playform.common.exception.SystemException;
import com.mattel.fuhu.playform.common.manager.DataPersistenceManager;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
public class BasicUserService implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(BasicUserService.class);

    @Autowired
    private DataPersistenceManager<QueenheadDaoFactory> dataManager;

    @Override
    public List<User> getAll() throws SystemException {
        UserDao userDao = dataManager.getDaoFactory().getUserDao();
        return userDao.getAllUsers();
    }

    @Override
    public User getUser(String account) throws SystemException {
        UserDao userDao = dataManager.getDaoFactory().getUserDao();
        User user = userDao.get(account);
        if (user == null) {
            throw new AccountNotExistedException();
        }
        return user;
    }

    @Override
    public void createUser(User user) throws SystemException {
        UserDao userDao = dataManager.getDaoFactory().getUserDao();
        String md5Hex = DigestUtils.md5Hex(user.getPassword()).toUpperCase();
        user.setPassword(md5Hex);
        userDao.create(user);
    }

    @Override
    public void updateUser(User user) throws SystemException {
        UserDao userDao = dataManager.getDaoFactory().getUserDao();
        User originUser = getUser(user.getAccount());
        if (user.getPassword() != null) {
            String md5Hex = DigestUtils.md5Hex(user.getPassword()).toUpperCase();
            originUser.setPassword(md5Hex);
        }
        if (user.getRole() != null) {
            originUser.setRole(user.getRole());
        }
        userDao.update(originUser);
    }

    @Override
    public void deleteUser(String account) throws SystemException {
        UserDao userDao = dataManager.getDaoFactory().getUserDao();
        userDao.delete(account);
    }
}
