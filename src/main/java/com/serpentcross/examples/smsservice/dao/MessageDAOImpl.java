package com.serpentcross.examples.smsservice.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.serpentcross.examples.smsservice.model.Response;
import com.serpentcross.examples.smsservice.util.DateConverter;
import com.serpentcross.examples.smsservice.util.MessageMapper;
import com.serpentcross.examples.smsservice.model.Message;
import com.serpentcross.examples.smsservice.util.SmsSender;

import javax.sql.DataSource;
import java.text.ParseException;
import java.util.List;

@Service("messageDAO")
@Transactional
public class MessageDAOImpl implements MessageDAO {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        log.info("DataSource is set!: ", new Object[]{dataSource});
    }

    public Response postMessage(Message message) throws ParseException {
        String SQL = "INSERT INTO message (phnum, msgdate, status, msgtxt) values (?, ?, ?, ?)";
        String sendStatus = "";
        SmsSender smsSender = new SmsSender();
        sendStatus = smsSender.sendSms(message.getPhnum(), message.getMsgtxt()) ? "Отправлено" : "Не отправлено";

        if(message.getPhnum() == "" || !message.getPhnum().matches("^79\\d{9}$")) {
            log.info("Номер в неверном формате: ", new Object[]{message.getPhnum()});
            return new Response("Ошибка! Номер в неверном формате!", 1);
        } else if (message.getMsgtxt() == "") {
            log.info("Пустой текст сообщения: ", new Object[]{message.getMsgtxt()});
            return new Response("Ошибка! Пустой текст сообщения!", 2);
        } else if (sendStatus == "Не отправлено"){
            try {
                jdbcTemplate.update(SQL, message.getPhnum(), DateConverter.getUnixFormat(), sendStatus, message.getMsgtxt());
                log.info("Статус сообщения: ", new Object[]{sendStatus, message.getMsgtxt()});
            }catch (DataAccessException ex) {
                log.info("Ошибка базы данных: ", new Object[]{ex});
                return new Response("Ошибка работы базы данных!", 4);
            }
            return new Response("Ошибка! Сообщение не отправлено!", 3);
        } else {
            try {
                jdbcTemplate.update(SQL, message.getPhnum(), DateConverter.getUnixFormat(), sendStatus, message.getMsgtxt());
            }catch (DataAccessException ex) {
                log.info("Ошибка базы данных: ", new Object[]{ex});
                return new Response("Ошибка работы базы данных!", 4);
            }
            return new Response("Сообщение отправлено!", 0);
        }
    }

    public Response deleteMessage(Message message) {
        try {
            String SQL = "DELETE FROM message WHERE id=?";
            jdbcTemplate.update(SQL, message.getId());
        } catch (RuntimeException runtimeException) {
            System.err.println("***NagiosHostDao::deleteObject, RuntimeException occurred, message follows.");
            System.err.println(runtimeException);
            throw runtimeException;
        }
        return new Response(null, 0);
    }

    public List<Message> loadAllMessages() {
        String SQL = "SELECT * FROM message ORDER BY id DESC";
        List<Message> messages = jdbcTemplate.query(SQL, new MessageMapper());
        log.info("result: ", new Object[]{messages});
        return messages;
    }
}
