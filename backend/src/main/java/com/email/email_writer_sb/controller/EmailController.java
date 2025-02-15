package com.email.email_writer_sb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.email.email_writer_sb.entity.EmailRequest;
import com.email.email_writer_sb.service.EmailGeneratorService;


@RestController
@RequestMapping("/api/email-reply")
@CrossOrigin("*")
public class EmailController {

    @Autowired
    private final EmailGeneratorService emailGeneratorService;

    public EmailController(EmailGeneratorService emailGeneratorService) {
        this.emailGeneratorService = emailGeneratorService;
    }

    @PostMapping
    public ResponseEntity<String> getAllEmployee(@RequestBody EmailRequest emailRequest){
        String response = emailGeneratorService.generateEmailReply(emailRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);   
    }
    
}
