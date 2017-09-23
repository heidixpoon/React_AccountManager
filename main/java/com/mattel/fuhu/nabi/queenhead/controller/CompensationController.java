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

package com.mattel.fuhu.nabi.queenhead.controller;

import com.mattel.fuhu.nabi.queenhead.entity.CompensationHistory;
import com.mattel.fuhu.nabi.queenhead.entity.Customer;
import com.mattel.fuhu.nabi.queenhead.entity.Session;
import com.mattel.fuhu.nabi.queenhead.entity.User;
import com.mattel.fuhu.nabi.queenhead.service.CompensationHistoryService;
import com.mattel.fuhu.nabi.queenhead.service.CompensationService;
import com.mattel.fuhu.nabi.queenhead.service.CouponService;
import com.mattel.fuhu.nabi.queenhead.service.SessionService;
import com.mattel.fuhu.nabi.queenhead.service.UserService;
import com.mattel.fuhu.nabi.queenhead.view.json.ExchangeCouponCodeRequestBody;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/compensations/codes")
public class CompensationController {

    private static final Logger logger = LoggerFactory.getLogger(CompensationController.class);

    @Autowired
    private CouponService couponService;

    private CompensationService compensationService;

    @Autowired
    private UserService userService;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private CompensationHistoryService compensationHistoryService;


    @ResponseStatus(value = HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value = "Exchange a coupon code for a compensation code", notes = "This API endpoint is for app to exchange a coupon code for a compensation code ")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "The resource has been exchanged."),
            @ApiResponse(code = 400, message = "The request parameters do not meet with all the requirements."),
            @ApiResponse(code = 401, message = "Valid authorization credentials must be provided."),
            @ApiResponse(code = 403, message = "The client must provide valid authorization credentials, update their access token, or make a new access request."),
            @ApiResponse(code = 500, message = "The issue should be reported to the system admin."),})
    public String exchangeCoupon( @ApiParam(value = "The input for exchange coupon", required = true)
                                       @RequestBody ExchangeCouponCodeRequestBody body){
        Customer customer = body.toCustomer(body.getEmail(), body.getFirstName(), body.getLastName(), body.getNabiUserKey());
        String compensationCode = couponService.exchange(customer, body.getCouponCode());
        return compensationCode;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ApiImplicitParams({
        @ApiImplicitParam(paramType = "header", name = "userKey", value = "User Key", required = true)
    })
    @ResponseStatus(value = HttpStatus.OK)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Get compensation codes successfully."),
        @ApiResponse(code = 400, message = "Bad request."),
        @ApiResponse(code = 401, message = "Unauthorized. Please provide valid authorization information."),
        @ApiResponse(code = 403, message = "Forbidden. Please provide authorization information, request for access  or update your access token."),
        @ApiResponse(code = 500, message = "Internal error. Please notify and report issue to system admin.")
    })
    public List<String> getCompensationCodes(@RequestAttribute("UserKey") String userKey) {
        logger.info("Start to get compensation codes by userKey: ", userKey);
        List<String> compensationCodes = compensationService.getCompensationCodesByUserKey(userKey);
        return compensationCodes;
    }

    @RequestMapping(value = "/{compensationCode}/redemptions", method = RequestMethod.POST)
    @ApiImplicitParams({
        @ApiImplicitParam(paramType = "header", name = "sessionId", value = "Session Id"),
        @ApiImplicitParam(paramType = "body", name = "remark", value = "Remark of redemption")
    })
    @ResponseStatus(value = HttpStatus.OK)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Redeem compensation code successfully."),
        @ApiResponse(code = 400, message = "Bad request."),
        @ApiResponse(code = 401, message = "Unauthorized. Please provide valid authorization information."),
        @ApiResponse(code = 403, message = "Forbidden. Please provide authorization information, request for access  or update your access token."),
        @ApiResponse(code = 500, message = "Internal error. Please notify and report issue to system admin.")
    })
    public void redeemCompensation(
        @ApiParam(value = "The compensation code", required = true) @PathVariable String compensationCode,
        @RequestAttribute("sessionId") String sessionId,
        @RequestAttribute("remark") String remark
    ) {
        logger.info("Start to redeem code: compensationCode:{}, remark:{}", compensationCode, remark);
        Session session = sessionService.get(sessionId);
        User user = session.getUser();
        compensationService.redeem(compensationCode, remark, user);
    }

    @RequestMapping(value = "/{compensationCode}/redemptions", method = RequestMethod.PUT)
    @ApiImplicitParams({
        @ApiImplicitParam(paramType = "header", name = "sessionId", value = "Session Id"),
        @ApiImplicitParam(paramType = "body", name = "remark", value = "Remark of redemption")
    })
    @ResponseStatus(value = HttpStatus.OK)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Update compensation remarks successfully."),
        @ApiResponse(code = 400, message = "Bad request."),
        @ApiResponse(code = 401, message = "Unauthorized. Please provide valid authorization information."),
        @ApiResponse(code = 403, message = "Forbidden. Please provide authorization information, request for access  or update your access token."),
        @ApiResponse(code = 500, message = "Internal error. Please notify and report issue to system admin.")
    })
    public void updateRedeemCodeRemark(
        @ApiParam(value = "The compensation code", required = true) @PathVariable String compensationCode,
        @RequestAttribute("sessionId") String sessionId,
        @RequestAttribute("remark") String remark
    ) {
        logger.info("Start to update compensation remarks");
        Session session = sessionService.get(sessionId);
        User user = session.getUser();
        compensationService.updateRemark(compensationCode, remark, user);
    }

    @RequestMapping(value = "/{compensationCode}/redemptions", method = RequestMethod.DELETE)
    @ApiImplicitParams({
        @ApiImplicitParam(paramType = "header", name = "sessionId", value = "Session Id"),
        @ApiImplicitParam(paramType = "body", name = "cancelReason", value = "Unredeem reason")
    })
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @ApiResponses(value = {
        @ApiResponse(code = 204, message = "Redeem compensation codes successfully."),
        @ApiResponse(code = 400, message = "Bad request."),
        @ApiResponse(code = 401, message = "Unauthorized. Please provide valid authorization information."),
        @ApiResponse(code = 403, message = "Forbidden. Please provide authorization information, request for access  or update your access token."),
        @ApiResponse(code = 500, message = "Internal error. Please notify and report issue to system admin.")
    })
    public void unredeemCode(
        @ApiParam(value = "The compensation code", required = true) @PathVariable String compensationCode,
        @RequestAttribute("sessionId") String sessionId,
        @RequestAttribute("cancelReason") String cancelReason
    ) {
        logger.info("Start to unredeem compensation.");
        Session session = sessionService.get(sessionId);
        User user = session.getUser();
        compensationService.unredeem(compensationCode, cancelReason, user);
    }

    @RequestMapping(value = "/{compensationCode}/redemptions/history", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Get compensation history successfully."),
        @ApiResponse(code = 400, message = "Bad request."),
        @ApiResponse(code = 401, message = "Unauthorized. Please provide valid authorization information."),
        @ApiResponse(code = 403, message = "Forbidden. Please provide authorization information, request for access  or update your access token."),
        @ApiResponse(code = 500, message = "Internal error. Please notify and report issue to system admin.")
    })
    public List<CompensationHistory> viewOperationHistory(
        @ApiParam(value = "The compensation code", required = true) @PathVariable String compensationCode
    ) {
        logger.info("Start to get operation history.");
        List<CompensationHistory> list = compensationHistoryService.getCompensationHistoryList(compensationCode);
        return list;
    }

}
