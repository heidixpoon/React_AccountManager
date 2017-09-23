package com.mattel.fuhu.nabi.queenhead.common.exception.coupon;

import com.mattel.fuhu.nabi.queenhead.common.exception.CouponServiceException;
import org.springframework.http.HttpStatus;


public class CouponCodeNotExistException extends CouponServiceException{
    private static final String ERROR_CODE = ERROR_CODE_PREFIX + "002";
    private static final String ERROR_MESSAGE = "Coupon code is not exist.";
    private static final HttpStatus HTTP_STATUS = HttpStatus.BAD_REQUEST;

    public CouponCodeNotExistException() {
        super(HTTP_STATUS, ERROR_CODE, ERROR_MESSAGE);
    }
}
