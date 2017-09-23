package com.mattel.fuhu.nabi.queenhead.common.exception.coupon;

import com.mattel.fuhu.nabi.queenhead.common.exception.CouponServiceException;
import org.springframework.http.HttpStatus;

public class CouponCodeEmptyException extends CouponServiceException{
    private static final String ERROR_CODE = ERROR_CODE_PREFIX + "001";
    private static final String ERROR_MESSAGE = "Coupon code cannot be null or empty.";
    private static final HttpStatus HTTP_STATUS = HttpStatus.BAD_REQUEST;

    public CouponCodeEmptyException() {
        super(HTTP_STATUS, ERROR_CODE, ERROR_MESSAGE);
    }
}
