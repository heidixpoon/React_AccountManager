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

package com.mattel.fuhu.nabi.queenhead.common.exception;

import com.mattel.fuhu.playform.common.exception.CommonException;
import org.springframework.http.HttpStatus;

public class QueenHeadException extends CommonException {
    public QueenHeadException(HttpStatus httpStatus, String errorCode, String message) {
        super(httpStatus, errorCode, message);
    }
}
