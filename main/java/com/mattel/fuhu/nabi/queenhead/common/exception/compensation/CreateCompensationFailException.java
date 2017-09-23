package com.mattel.fuhu.nabi.queenhead.common.exception.compensation;

import com.mattel.fuhu.nabi.queenhead.common.exception.CompensationServiceException;
import org.springframework.http.HttpStatus;

/**
 * <p>
 * </p>
 * <p>
 *     Inception date: 2017-09-18
 * </p>
 * <p>
 *     Copyright &copy; 2017 Fuhu, Inc. All rights reserved.
 * </p>
 *
 * @author Jacob Huang (<a href="jacob.huang@fuhu.com">jacob.huang@fuhu.com</a>)
 *
 */
/**
 * Created by jacob.huang on 2017/9/18.
 */
public class CreateCompensationFailException extends CompensationServiceException {

    private static final String ERROR_CODE = ERROR_CODE_PREFIX + "009";
    private static final String ERROR_MESSAGE = "Create compensation fail.";
    private static final HttpStatus HTTP_STATUS = HttpStatus.BAD_REQUEST;

    public CreateCompensationFailException () {
        super (HTTP_STATUS, ERROR_CODE, ERROR_MESSAGE);
    }
}
