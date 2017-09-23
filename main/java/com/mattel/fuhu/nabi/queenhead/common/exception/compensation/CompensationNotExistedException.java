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

package com.mattel.fuhu.nabi.queenhead.common.exception.compensation;

import com.mattel.fuhu.nabi.queenhead.common.exception.CompensationServiceException;
import org.springframework.http.HttpStatus;

public class CompensationNotExistedException extends CompensationServiceException {
    private static final String ERROR_CODE = ERROR_CODE_PREFIX + "006";
    private static final String ERROR_MESSAGE = "Compensation is not existed.";
    private static final HttpStatus HTTP_STATUS = HttpStatus.NOT_FOUND;

    public CompensationNotExistedException() {
        super(HTTP_STATUS, ERROR_CODE, ERROR_MESSAGE);
    }
}
