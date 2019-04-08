package com.serpentcross.examples.smsservice.util;

import org.springframework.jdbc.core.RowMapper;
import com.serpentcross.examples.smsservice.model.Message;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MessageMapper implements RowMapper<Message> {
    public Message mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Message message = new Message();
        message.setId(resultSet.getString("id"));
        message.setPhnum(resultSet.getString("phnum"));
        message.setMsgdate(DateConverter.getRegularFormat(resultSet.getLong("msgDate")));
        message.setStatus(resultSet.getString("status"));
        message.setMsgtxt(resultSet.getString("msgtxt"));
        return message;
    }
}