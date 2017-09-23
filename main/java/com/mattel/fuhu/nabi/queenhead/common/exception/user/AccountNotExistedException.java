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

public class AccountNotExistedException extends UserServiceException {
    private static final String ERROR_CODE = ERROR_CODE_PREFIX + "001";
    private static final String ERROR_MESSAGE = "User account is not existed.";
    private static final HttpStatus HTTP_STATUS = HttpStatus.NOT_FOUND;

    public AccountNotExistedException() {
        super(HTTP_STATUS, ERROR_CODE, ERROR_MESSAGE);
    }
}
