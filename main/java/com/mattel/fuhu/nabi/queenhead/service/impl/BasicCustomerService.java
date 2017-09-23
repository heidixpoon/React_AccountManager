/*
 * Copyright (c) 2017 Mattel, Inc. All rights reserved.
 *
 * All information and code contained herein is the property of Mattel, Inc.
 *
 * Any unauthorized use, storage, duplication, and redistribution of this
 * material without written permission from Mattel, Inc. is strictly prohibited.
 */
package com.mattel.fuhu.nabi.queenhead.service.impl;

import com.mattel.fuhu.nabi.queenhead.common.exception.customer.CustomerCreateFailException;
import com.mattel.fuhu.nabi.queenhead.common.exception.customer.CustomerInfoLackingException;
import com.mattel.fuhu.nabi.queenhead.common.exception.customer.CustomerNotExistException;
import com.mattel.fuhu.nabi.queenhead.persistence.CustomerDao;
import com.mattel.fuhu.nabi.queenhead.persistence.QueenheadDaoFactory;
import com.mattel.fuhu.playform.common.exception.system.DaoException;
import com.mattel.fuhu.playform.common.manager.DataPersistenceManager;
import com.mattel.fuhu.playform.common.util.StringUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mattel.fuhu.nabi.queenhead.entity.Customer;
import com.mattel.fuhu.nabi.queenhead.service.CustomerService;

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
 */

@Service
public class BasicCustomerService implements CustomerService{

    private static Logger logger = LoggerFactory.getLogger(BasicCustomerService.class);

    @Autowired
    private DataPersistenceManager<QueenheadDaoFactory> dataManager;

    @Override
    public Customer getCustomerByEmail(String email) {
        CustomerDao customerDao = dataManager.getDaoFactory().getCustomerDao();
        Customer customer = customerDao.getCustomerByEmail(email);
        if(customer == null) {
            logger.warn("Customer does not exist, customer email: {}", email);
            throw new CustomerNotExistException();
        }
        return customer;
    }

    @Override
    public Customer getCustomerByCode(String code) {
        CustomerDao customerDao = dataManager.getDaoFactory().getCustomerDao();
        Customer customer = customerDao.getCustomerByCode(code);
        if(customer == null) {
            logger.warn("Customer does not exist, compensation code: {}", code);
            throw new CustomerNotExistException();
        }
        return customer;
    }

    @Override
    public Customer getCustomerByNabiUserKey(String nabiUserKey) {
        //TODO
        return null;
    }

    @Override
    public void createCustomer(Customer customer) {
        CustomerDao customerDao = dataManager.getDaoFactory().getCustomerDao();
        if (StringUtil.isNullOrEmpty(customer.getNabiUserKey()) ||
                StringUtil.isNullOrEmpty(customer.getEmail())) {
            logger.warn("Customer's info is lacking");
            throw new CustomerInfoLackingException();
        }
        try {
            customerDao.create(customer);
        } catch (DaoException e) {
            logger.error("{}", e);
            throw new CustomerCreateFailException();
        }
    }

}
