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

package com.mattel.fuhu.nabi.queenhead.common.exception.report;

import com.mattel.fuhu.nabi.queenhead.common.exception.ReportServiceException;
import org.springframework.http.HttpStatus;

public class DateLackingException extends ReportServiceException {
    private static final String ERROR_CODE = ERROR_CODE_PREFIX + "001";
    private static final String ERROR_MESSAGE = "Date fiels is lacking.";
    private static final HttpStatus HTTP_STATUS = HttpStatus.BAD_REQUEST;

    public DateLackingException() {
        super(HTTP_STATUS, ERROR_CODE, ERROR_MESSAGE);
    }
}
