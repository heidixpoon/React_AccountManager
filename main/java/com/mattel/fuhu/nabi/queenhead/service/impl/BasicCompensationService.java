/*
 * Copyright (c) 2017 Mattel, Inc. All rights reserved.
 *
 * All information and code contained herein is the property of Mattel, Inc.
 *
 * Any unauthorized use, storage, duplication, and redistribution of this
 * material without written permission from Mattel, Inc. is strictly prohibited.
 */
package com.mattel.fuhu.nabi.queenhead.service.impl;

import com.mattel.fuhu.nabi.queenhead.common.exception.compensation.CompensationCodeEmptyException;
import com.mattel.fuhu.nabi.queenhead.common.exception.compensation.CompensationNotExistedException;
import com.mattel.fuhu.nabi.queenhead.common.exception.compensation.CreateCompensationFailException;
import com.mattel.fuhu.nabi.queenhead.common.exception.compensation.UserKeyEmptyException;
import com.mattel.fuhu.nabi.queenhead.common.exception.customer.CustomerNotExistException;
import com.mattel.fuhu.nabi.queenhead.entity.Compensation;
import com.mattel.fuhu.nabi.queenhead.entity.CompensationType;
import com.mattel.fuhu.nabi.queenhead.entity.Customer;
import com.mattel.fuhu.nabi.queenhead.entity.User;
import com.mattel.fuhu.nabi.queenhead.persistence.CompensationDao;
import com.mattel.fuhu.nabi.queenhead.persistence.CouponDao;
import com.mattel.fuhu.nabi.queenhead.persistence.CustomerDao;
import com.mattel.fuhu.nabi.queenhead.persistence.QueenheadDaoFactory;
import com.mattel.fuhu.nabi.queenhead.service.CompensationService;
import com.mattel.fuhu.playform.common.exception.system.DaoException;
import com.mattel.fuhu.playform.common.manager.DataPersistenceManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * </p>
 * <p>
 * Inception date: 2017-09-13
 * </p>
 * <p>
 * Copyright &copy; 2017 Fuhu, Inc. All rights reserved.
 * </p>
 *
 * @author Ken Wang (<a href="mailto:ken.wang@fuhu.com">ken.wang@fuhu.com</a>)
 * @author Yingray Lu (<a href="mailto:yingray.lu@fuhu.com">yingray.lu@fuhu.com</a>)
 */

@Service
public class BasicCompensationService implements CompensationService {

    private static final Logger logger = LoggerFactory.getLogger(BasicCompensationService.class);
    private static final boolean CREATE_REDEEMED = false;

    @Autowired
    private DataPersistenceManager<QueenheadDaoFactory> dataPersistenceManager;

    @Override
    public void createCompensation(String couponCode, String compensationCode, CompensationType compensationType, String nabiUserKey) {
        CouponDao couponDao = dataPersistenceManager.getDaoFactory().getCouponDao();
        CustomerDao customerDao = dataPersistenceManager.getDaoFactory().getCustomerDao();
        Customer customer = customerDao.getCustomerByNabiUserKey(nabiUserKey);
        if (customer == null) {
            logger.warn("Customer does not exist, Customer nabiUserKey: {}", nabiUserKey);
            throw new CustomerNotExistException();
        }
        // Set coupon as used
        try {
            couponDao.setCouponAsUsed(couponCode);
        } catch (DaoException e) {
            logger.warn("Update coupon as used fail, Coupon Code: {}", couponCode);
            throw new CreateCompensationFailException();
        }
        // Create compensation
        Compensation compensation = new Compensation();
        compensation.setCompensationCode(compensationCode);
        compensation.setCouponCode(couponCode);
        compensation.setRedeemed(CREATE_REDEEMED);
        compensation.setType(compensationType);
        CompensationDao compensationDao = dataPersistenceManager.getDaoFactory().getCompensationDao();
        try {
            compensationDao.create(compensation, nabiUserKey);
        } catch (DaoException e) {
            logger.warn("Create compensation fail.");
            // Rollback
            try {
                couponDao.setCouponAsNotUsed(couponCode);
            } catch (DaoException de){
                logger.warn("Rollback Fail, Coupon Code: {}", couponCode);
            } finally {
                throw new CreateCompensationFailException();
            }
        }
    }

    @Override
    public Compensation getCompensationByCode(String compensationCode) {
        if (compensationCode.isEmpty()) {
            throw new CompensationCodeEmptyException();
        }
        CompensationDao compensationDao = dataPersistenceManager.getDaoFactory().getCompensationDao();
        Compensation compensation = compensationDao.getCompensationByCode(compensationCode);
        if (compensation == null) {
            throw new CompensationNotExistedException();
        }
        return compensation;
    }

    @Override
    public List<String> getCompensationCodesByUserKey(String userKey) {
        if (userKey.isEmpty()) {
            throw new UserKeyEmptyException();
        }
        CompensationDao compensationDao = dataPersistenceManager.getDaoFactory().getCompensationDao();
        List<String> compensationCodes = compensationDao.getCompensationsByUserKey(userKey);
        return compensationCodes;
    }

    @Override
    public void redeem(String compensationCode, String remark, User user) {
        // TODO Auto-generated method stub
    }

    @Override
    public void unredeem(String compensationCode, String cancelReason, User user) {
        // TODO Auto-generated method stub
    }

    @Override
    public void updateRemark(String compensationCode, String remark, User user) {
        // TODO Auto-generated method stub
    }


}
