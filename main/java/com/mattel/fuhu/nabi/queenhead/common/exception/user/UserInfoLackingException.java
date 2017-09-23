/*
 *
 * Copyright (c) 2017 Mattel, Inc. All rights reserved.
 *
 * All information and code contained herein is the property of Mattel, Inc.
 *
 * Any unauthorized use, storage, duplication, and redistribution of this
 * material without written permission from Mattel, Inc. is strictly prohibited.
 *
 */

package com.mattel.fuhu.nabi.queenhead.common.exception.user;

import com.mattel.fuhu.nabi.queenhead.common.exception.UserServiceException;
import org.springframework.http.HttpStatus;

public class UserInfoLackingException extends UserServiceException {
    private static final String ERROR_CODE = ERROR_CODE_PREFIX + "002";
    private static final String ERROR_MESSAGE = "User info is lacking.";
    private static final HttpStatus HTTP_STATUS = HttpStatus.BAD_REQUEST;

    public UserInfoLackingException() {
        super(HTTP_STATUS, ERROR_CODE, ERROR_MESSAGE);
    }

    public UserInfoLackingException(String message) {
        super(HTTP_STATUS, ERROR_CODE, message);
    }
}
