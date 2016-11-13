package ru.svyaznoy.test.dao;

import ru.svyaznoy.test.model.Message;
import ru.svyaznoy.test.model.Response;

import javax.sql.DataSource;
import java.text.ParseException;
import java.util.List;

public interface MessageDAO {

    void setDataSource(DataSource ds);
    Response postMessage(Message message) throws ParseException;
    Response deleteMessage(Message message);
    List<Message> loadAllMessages();
}
