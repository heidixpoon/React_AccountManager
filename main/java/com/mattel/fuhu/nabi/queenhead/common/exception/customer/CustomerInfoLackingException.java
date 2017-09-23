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

package com.mattel.fuhu.nabi.queenhead.common.exception.customer;

import com.mattel.fuhu.nabi.queenhead.common.exception.CustomerServiceException;
import org.springframework.http.HttpStatus;

/**
 * Created by jacob.huang on 2017/9/14.
 */
public class CustomerInfoLackingException extends CustomerServiceException{
    private static final String ERROR_CODE = ERROR_CODE_PREFIX + "002";
    private static final String ERROR_MESSAGE = "Customer info is lacking, nabiUserKey or email can not be null or empty.";
    private static final HttpStatus HTTP_STATUS = HttpStatus.BAD_REQUEST;

    public CustomerInfoLackingException () {
        super (HTTP_STATUS, ERROR_CODE, ERROR_MESSAGE);
    }
}
