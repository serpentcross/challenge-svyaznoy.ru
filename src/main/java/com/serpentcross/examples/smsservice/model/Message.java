package com.serpentcross.examples.smsservice.model;

import lombok.Data;

@Data
public class Message {
    String id;
    String phnum;
    String msgdate;
    String status;
    String msgtxt;
}