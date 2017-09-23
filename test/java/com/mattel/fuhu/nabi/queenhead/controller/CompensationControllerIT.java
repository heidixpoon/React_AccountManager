package com.mattel.fuhu.nabi.queenhead.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mattel.fuhu.nabi.queenhead.QueenheadServiceApplication;
import com.mattel.fuhu.nabi.queenhead.view.json.ExchangeCouponCodeRequestBody;
import com.mattel.fuhu.playform.common.util.ObjectMapperUtil;
import com.mattel.fuhu.playform.common.util.RestfulApiUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Properties;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = QueenheadServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CompensationControllerIT {

    private static final Logger logger = LoggerFactory.getLogger(CompensationControllerIT.class);
    private static final String PROPERTY_PATH = "env.properties";
    private static final ObjectMapper mapper = ObjectMapperUtil.getInstance();

    @LocalServerPort
    private int port;

    private static String server;

    static {
        Properties prop = new Properties();
        try {
            prop.load(CompensationControllerIT.class.getClassLoader().getResourceAsStream(PROPERTY_PATH));
            server = prop.getProperty("service.server");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test() throws IOException {
        String exchangeUrl = server + ":" + port + "/api/v1/compensations/codes";

        logger.info("Exchange compensation code...");
        logger.info("Exchange compensation code URL:{} ", exchangeUrl);

        ExchangeCouponCodeRequestBody bodyWrapper = new ExchangeCouponCodeRequestBody();
        bodyWrapper.setEmail("john.smith@fuhu.com");
        bodyWrapper.setLastName("Smith");
        bodyWrapper.setFirstName("John");
        bodyWrapper.setNabiUserKey("nabi-user-key");
        bodyWrapper.setCouponCode("invalidCode");
        String body = mapper.writeValueAsString(bodyWrapper);

        HttpURLConnection conn = RestfulApiUtil.sendRequest(exchangeUrl, body,
                HttpMethod.POST);
        assertEquals(HttpStatus.BAD_REQUEST.value(), conn.getResponseCode());

    }

}
