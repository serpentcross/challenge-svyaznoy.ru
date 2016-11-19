package com.serpentcross.examples.smsservice.controller;

import com.serpentcross.examples.smsservice.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.serpentcross.examples.smsservice.dao.MessageDAO;
import com.serpentcross.examples.smsservice.model.Response;

import java.text.ParseException;
import java.util.List;

@RestController
public class SvRestController {

    @Autowired
    MessageDAO messageDAO;

    @ExceptionHandler(DataAccessException.class)
    @ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public String handleException(DataAccessException ex) {
        return "������! ����������� ����������� � ���� ������!";
    }

    @RequestMapping(value = "/messages", method = RequestMethod.GET)
    public ResponseEntity<List<Message>> loadMessages() throws DataAccessException {
        List<Message> messages = messageDAO.loadAllMessages();
        if(messages.isEmpty()){
            return new ResponseEntity<List<Message>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Message>>(messages, HttpStatus.OK);
    }

    @RequestMapping(value = "/send", method = RequestMethod.POST, produces="application/json", consumes="application/json")
    public Response sendMessage(@RequestBody Message message) throws ParseException {
        return messageDAO.postMessage(message);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST, consumes="application/json")
    public Response deleteRow(@RequestBody Message message) throws DataAccessException {
        return messageDAO.deleteMessage(message);
    }
}