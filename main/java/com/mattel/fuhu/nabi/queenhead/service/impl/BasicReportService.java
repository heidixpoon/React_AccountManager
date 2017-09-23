/*
 * Copyright (c) 2017 Mattel, Inc. All rights reserved.
 *
 * All information and code contained herein is the property of Mattel, Inc.
 *
 * Any unauthorized use, storage, duplication, and redistribution of this
 * material without written permission from Mattel, Inc. is strictly prohibited.
 */
package com.mattel.fuhu.nabi.queenhead.service.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.mattel.fuhu.nabi.queenhead.entity.CompensationType;
import com.mattel.fuhu.nabi.queenhead.service.ReportService;

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
public class BasicReportService implements ReportService {

    @Override
    public Integer getUsedCouponCount() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Integer getRedemptionCount() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Integer getRedemptionCountByDateRange(Date from, Date to) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Integer getUsedCouponCountByDataRange(Date from, Date to) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Integer getRedemptionCount(CompensationType type) {
        // TODO Auto-generated method stub
        return null;
    }

}
