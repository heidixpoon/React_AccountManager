/*
 * Copyright (c) 2017 Mattel, Inc. All rights reserved.
 *
 * All information and code contained herein is the property of Mattel, Inc.
 *
 * Any unauthorized use, storage, duplication, and redistribution of this
 * material without written permission from Mattel, Inc. is strictly prohibited.
 */
package com.mattel.fuhu.nabi.queenhead.service;

import java.util.List;

import com.mattel.fuhu.nabi.queenhead.entity.User;
import com.mattel.fuhu.playform.common.exception.SystemException;

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

public interface UserService {

    List<User> getAll() throws SystemException; // admin only

    User getUser(String account) throws SystemException;

    void createUser(User user) throws SystemException;

    void updateUser(User user) throws SystemException;

    void deleteUser(String account) throws SystemException;

}
