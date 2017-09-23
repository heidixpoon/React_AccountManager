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

package com.mattel.fuhu.nabi.queenhead.common.exception.compensationHistory;

import com.mattel.fuhu.nabi.queenhead.common.exception.CompensationHistoryServiceException;
import org.springframework.http.HttpStatus;

public class CompensationHistoryInfoLackingException extends CompensationHistoryServiceException {
    private static final String ERROR_CODE = ERROR_CODE_PREFIX + "002";
    private static final String ERROR_MESSAGE = "Compensation history info is lacking.";
    private static final HttpStatus HTTP_STATUS = HttpStatus.BAD_REQUEST;

    public CompensationHistoryInfoLackingException() {
        super(HTTP_STATUS, ERROR_CODE, ERROR_MESSAGE);
    }
}
