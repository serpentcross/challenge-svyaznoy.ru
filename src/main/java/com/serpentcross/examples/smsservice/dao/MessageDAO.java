package com.serpentcross.examples.smsservice.dao;

import com.serpentcross.examples.smsservice.model.Message;
import com.serpentcross.examples.smsservice.model.Response;

import javax.sql.DataSource;
import java.text.ParseException;
import java.util.List;

public interface MessageDAO {

    void setDataSource(DataSource ds);
    Response postMessage(Message message) throws ParseException;
    Response deleteMessage(Message message);
    List<Message> loadAllMessages();
}
