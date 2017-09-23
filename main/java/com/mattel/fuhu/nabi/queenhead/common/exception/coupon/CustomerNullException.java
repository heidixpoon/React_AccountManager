package com.mattel.fuhu.nabi.queenhead.common.exception.coupon;

import com.mattel.fuhu.nabi.queenhead.common.exception.CouponServiceException;
import org.springframework.http.HttpStatus;

public class CustomerNullException extends CouponServiceException{
    private static final String ERROR_CODE = ERROR_CODE_PREFIX + "003";
    private static final String ERROR_MESSAGE = "Customer cannot be null.";
    private static final HttpStatus HTTP_STATUS = HttpStatus.BAD_REQUEST;

    public CustomerNullException() {
        super(HTTP_STATUS, ERROR_CODE, ERROR_MESSAGE);
    }
}
