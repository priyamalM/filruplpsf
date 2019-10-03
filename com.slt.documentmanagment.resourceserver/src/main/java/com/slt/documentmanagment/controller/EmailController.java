package com.slt.documentmanagment.controller;

import com.slt.documentmanagment.service.EmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

import static com.slt.documentmanagment.configuration.ApplicationRoles.ADMIN;
import static com.slt.documentmanagment.configuration.ApplicationRoles.USER;

@RestController()
@RequestMapping("/email")
public class EmailController {

    @Autowired
    EmailServiceImpl emailService;

    @RequestMapping(value = "/send",method = RequestMethod.POST)
    @RolesAllowed({ADMIN,USER})
    public String sendEmail(){
        emailService.sendMessage("priyamalm@mobitel.lk","email verification"," email verify msg");
        return "send";
    }

}