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

package com.mattel.fuhu.nabi.queenhead;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Import;

import com.mattel.fuhu.nabi.queenhead.persistence.s3.manager.S3PersistenceConfig;
import com.mattel.fuhu.playform.common.exception.ExceptionHandlerConfig;

@SpringBootApplication
@Import(value = { S3PersistenceConfig.class, ExceptionHandlerConfig.class })
@ServletComponentScan
public class QueenheadServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(QueenheadServiceApplication.class, args);
    }
}
