package com.serpentcross.examples.smsservice.controller;

import com.serpentcross.examples.smsservice.dao.MessageDAO;
import com.serpentcross.examples.smsservice.model.Message;
import com.serpentcross.examples.smsservice.model.Response;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.dao.DataAccessException;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

import java.util.List;

@RestController
public class SvRestController {

    private final MessageDAO messageDAO;

    @Autowired
    public SvRestController(MessageDAO messageDAO) {
        this.messageDAO = messageDAO;
    }

    @ExceptionHandler(DataAccessException.class)
    @ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public String handleException(DataAccessException ex) {
        return "������! ����������� ����������� � ���� ������!";
    }

    @GetMapping(value = "/messages")
    public ResponseEntity<List<Message>> loadMessages() throws DataAccessException {
        List<Message> messages = messageDAO.loadAllMessages();
        if(messages.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    @PostMapping(value = "/send", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Response sendMessage(@RequestBody Message message) throws ParseException {
        return messageDAO.postMessage(message);
    }

    @DeleteMapping(value = "/delete", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Response deleteRow(@RequestBody Message message) throws DataAccessException {
        return messageDAO.deleteMessage(message);
    }
}