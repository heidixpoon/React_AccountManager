/*
 * Copyright (c) 2017 Mattel, Inc. All rights reserved.
 *
 * All information and code contained herein is the property of Mattel, Inc.
 *
 * Any unauthorized use, storage, duplication, and redistribution of this
 * material without written permission from Mattel, Inc. is strictly prohibited.
 */
package com.mattel.fuhu.nabi.queenhead.service;

import java.util.Date;

import com.mattel.fuhu.nabi.queenhead.entity.CompensationType;

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

public interface ReportService {
    
    Integer getUsedCouponCount();

    Integer getRedemptionCount();

    Integer getRedemptionCountByDateRange(Date from, Date to);

    Integer getUsedCouponCountByDataRange(Date from, Date to);
    
    Integer getRedemptionCount(CompensationType type);
}
