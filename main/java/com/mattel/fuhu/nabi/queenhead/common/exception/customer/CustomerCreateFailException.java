package com.mattel.fuhu.nabi.queenhead.common.exception.customer;

import com.mattel.fuhu.nabi.queenhead.common.exception.CustomerServiceException;
import org.springframework.http.HttpStatus;

/**
 * <p>
 * </p>
 * <p>
 * Inception date: 2017-09-20
 * </p>
 * <p>
 * Copyright &copy; 2017 Fuhu, Inc. All rights reserved.
 * </p>
 *
 * @author Jacob Huang (<a href="jacob.huang@fuhu.com">jacob.huang@fuhu.com</a>)
 */
public class CustomerCreateFailException extends CustomerServiceException {
    private static final String ERROR_CODE = ERROR_CODE_PREFIX + "003";
    private static final String ERROR_MESSAGE = "Customer info create fail.";
    private static final HttpStatus HTTP_STATUS = HttpStatus.BAD_REQUEST;

    public CustomerCreateFailException() {
        super(HTTP_STATUS, ERROR_CODE, ERROR_MESSAGE);
    }
}
