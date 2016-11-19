package com.serpentcross.examples.smsservice.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class SmsSender {
    final Logger log = LoggerFactory.getLogger(getClass());

    final Random random = new Random();
    public boolean sendSms(String phone, String message){
        boolean success = random.nextBoolean();
        log.info("message sent to {} with status {}:\n{}", new Object[]{phone, success, message});
        return success;
    }
}