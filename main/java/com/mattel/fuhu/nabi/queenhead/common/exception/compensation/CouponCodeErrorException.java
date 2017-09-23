package com.mattel.fuhu.nabi.queenhead.common.exception.compensation;

import com.mattel.fuhu.nabi.queenhead.common.exception.CompensationServiceException;
import org.springframework.http.HttpStatus;

/**
 * <p>
 * </p>
 * <p>
 *     Inception date: 2017-09-15
 * </p>
 * <p>
 *     Copyright &copy; 2017 Fuhu, Inc. All rights reserved.
 * </p>
 *
 * @author Jacob Huang (<a href="jacob.huang@fuhu.com">jacob.huang@fuhu.com</a>)
 *
 */
public class CouponCodeErrorException extends CompensationServiceException{

    private static final String ERROR_CODE = ERROR_CODE_PREFIX + "008";
    private static final String ERROR_MESSAGE = "Coupon code error.";
    private static final HttpStatus HTTP_STATUS = HttpStatus.NOT_FOUND;

    public CouponCodeErrorException() {
        super (HTTP_STATUS, ERROR_CODE, ERROR_MESSAGE);
    }
}
