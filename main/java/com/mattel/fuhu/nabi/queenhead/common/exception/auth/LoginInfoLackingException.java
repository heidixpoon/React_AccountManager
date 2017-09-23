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

package com.mattel.fuhu.nabi.queenhead.common.exception.auth;

import com.mattel.fuhu.nabi.queenhead.common.exception.AuthServiceException;
import org.springframework.http.HttpStatus;

public class LoginInfoLackingException extends AuthServiceException {
    private static final String ERROR_CODE = ERROR_CODE_PREFIX + "001";
    private static final String ERROR_MESSAGE = "Login information is lacking. Account or password fields can not be null or empty.";
    private static final HttpStatus HTTP_STATUS = HttpStatus.BAD_REQUEST;

    public LoginInfoLackingException() {
        super(HTTP_STATUS, ERROR_CODE, ERROR_MESSAGE);
    }
}
