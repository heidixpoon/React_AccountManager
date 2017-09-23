/*
 * Copyright (c) 2017 Mattel, Inc. All rights reserved.
 *
 * All information and code contained herein is the property of Mattel, Inc.
 *
 * Any unauthorized use, storage, duplication, and redistribution of this
 * material without written permission from Mattel, Inc. is strictly prohibited.
 */
package com.mattel.fuhu.nabi.queenhead.service.impl;

import com.mattel.fuhu.nabi.queenhead.common.exception.CouponServiceException;
import com.mattel.fuhu.nabi.queenhead.common.exception.coupon.CouponCodeEmptyException;
import com.mattel.fuhu.nabi.queenhead.common.exception.coupon.CouponCodeNotExistException;
import com.mattel.fuhu.nabi.queenhead.common.exception.coupon.CustomerNullException;
import com.mattel.fuhu.nabi.queenhead.entity.Compensation;
import com.mattel.fuhu.nabi.queenhead.entity.CompensationType;
import com.mattel.fuhu.nabi.queenhead.entity.Coupon;
import com.mattel.fuhu.nabi.queenhead.entity.Customer;
import com.mattel.fuhu.nabi.queenhead.persistence.CompensationDao;
import com.mattel.fuhu.nabi.queenhead.persistence.CouponDao;
import com.mattel.fuhu.nabi.queenhead.persistence.CustomerDao;
import com.mattel.fuhu.nabi.queenhead.persistence.QueenheadDaoFactory;
import com.mattel.fuhu.nabi.queenhead.service.CompensationService;
import com.mattel.fuhu.nabi.queenhead.service.CouponService;
import com.mattel.fuhu.nabi.queenhead.service.CustomerService;
import com.mattel.fuhu.playform.common.exception.system.DaoException;
import com.mattel.fuhu.playform.common.manager.DataPersistenceManager;
import com.mattel.fuhu.playform.common.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

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
 * @author Jerry Chen
 * (<a href="mailto:jerry.chen@fuhu.com">jerry.chen@fuhu.com</a>)
 */

@Service
public class BasicCouponService implements CouponService {

    @Autowired
    private CompensationService compensationService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private DataPersistenceManager<QueenheadDaoFactory> dataManager;

    @Override
    public String exchange(Customer customer, String couponCode) {
        if (StringUtil.isNullOrEmpty(couponCode)) {
            throw new CouponCodeEmptyException();
        }

        if (customer == null) {
            throw new CustomerNullException();
        }

        String compensationCode = getCompensationCode(couponCode);
        if (compensationCode == null) {
            throw new CouponCodeNotExistException();
        }

        boolean checked = checkCustomerExist(customer.getEmail());
        if (!checked) {
            customerService.createCustomer(customer);
        }

        bindCompensation(customer, couponCode, compensationCode);

        return compensationCode;
    }

    private String getCompensationCode(String couponCode) {
        try{
            CouponDao couponDao = dataManager.getDaoFactory().getCouponDao();
            Coupon coupon = couponDao.getCoupon(couponCode);

            if (coupon == null){
                // TODO: need to deal this scenario.
            }
            return coupon.getCompesationCode();
        }catch(DaoException e){
            // TODO: need to finish this excpetion content.
            throw new CouponServiceException(HttpStatus.BAD_REQUEST, null, "");
        }
    }

    private boolean checkCustomerExist(String email) {
        CustomerDao customerDao = dataManager.getDaoFactory().getCustomerDao();
        Customer customer = customerDao.getCustomerByEmail(email);
        if (customer == null) {
            return false;
        } else {
            return true;
        }
    }

    private void bindCompensation(Customer customer, String couponCode, String compensationCode) {
        CompensationDao compensationDao = dataManager.getDaoFactory().getCompensationDao();
        Compensation compensation = compensationDao.getCompensationByCode(compensationCode);
        if (compensation == null) {
            compensationService.createCompensation(couponCode, compensationCode, CompensationType.COUPON, customer.getNabiUserKey());
        }

    }
}
