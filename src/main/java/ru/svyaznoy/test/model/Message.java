package ru.svyaznoy.test.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class Message {
    @Getter @Setter String id;
    @Getter @Setter String phnum;
    @Getter @Setter String msgdate;
    @Getter @Setter String status;
    @Getter @Setter String msgtxt;
}
