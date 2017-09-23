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

package com.mattel.fuhu.nabi.queenhead.common.exception.session;

import com.mattel.fuhu.nabi.queenhead.common.exception.SessionServiceException;
import org.springframework.http.HttpStatus;

public class SessionExpiredException extends SessionServiceException {
    private static final String ERROR_CODE = ERROR_CODE_PREFIX + "003";
    private static final String ERROR_MESSAGE = "Session id is expired.";
    private static final HttpStatus HTTP_STATUS = HttpStatus.BAD_REQUEST;

    public SessionExpiredException() {
        super(HTTP_STATUS, ERROR_CODE, ERROR_MESSAGE);
    }
}
