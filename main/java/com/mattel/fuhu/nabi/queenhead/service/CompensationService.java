/*
 * Copyright (c) 2017 Mattel, Inc. All rights reserved.
 *
 * All information and code contained herein is the property of Mattel, Inc.
 *
 * Any unauthorized use, storage, duplication, and redistribution of this
 * material without written permission from Mattel, Inc. is strictly prohibited.
 */
package com.mattel.fuhu.nabi.queenhead.service;

import java.util.List;

import com.mattel.fuhu.nabi.queenhead.entity.Compensation;
import com.mattel.fuhu.nabi.queenhead.entity.CompensationType;
import com.mattel.fuhu.nabi.queenhead.entity.User;

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

public interface CompensationService {

    void createCompensation(String couponCode, String compensationCode, CompensationType compensationType, String nabiUserKey);

    Compensation getCompensationByCode(String compensationCode);

    List<String> getCompensationCodesByUserKey(String userKey); // for popup message

    void redeem(String compensationCode, String remark, User user);

    void unredeem(String compensationCode, String cancelReason, User user);

    void updateRemark(String compensationCode, String remark, User user);

}
