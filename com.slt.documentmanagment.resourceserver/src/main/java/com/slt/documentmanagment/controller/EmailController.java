package com.slt.documentmanagment.controller;

import com.slt.documentmanagment.service.EmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/email")
public class EmailController {

    @Autowired
    EmailServiceImpl emailService;

    @RequestMapping(value = "/send",method = RequestMethod.POST)
    public String sendEmail(){
        emailService.sendMessage("priyamalm@mobitel.lk","email verification"," email verify msg");
        return "send";
    }

}