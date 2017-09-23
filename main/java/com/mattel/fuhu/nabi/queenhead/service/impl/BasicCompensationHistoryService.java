/*
 * Copyright (c) 2017 Mattel, Inc. All rights reserved.
 *
 * All information and code contained herein is the property of Mattel, Inc.
 *
 * Any unauthorized use, storage, duplication, and redistribution of this
 * material without written permission from Mattel, Inc. is strictly prohibited.
 */
package com.mattel.fuhu.nabi.queenhead.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mattel.fuhu.nabi.queenhead.entity.CompensationHistory;
import com.mattel.fuhu.nabi.queenhead.service.CompensationHistoryService;

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
public class BasicCompensationHistoryService implements CompensationHistoryService {

    @Override
    public void createCompensationHistory(CompensationHistory history) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public List<CompensationHistory> getCompensationHistoryList(String compensationCode) {
        // TODO Auto-generated method stub
        return null;
    }

}
