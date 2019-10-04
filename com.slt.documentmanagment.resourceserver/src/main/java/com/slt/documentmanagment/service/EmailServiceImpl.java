package com.slt.documentmanagment.service;

import com.slt.documentmanagment.configuration.SmtpPropsConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class EmailServiceImpl implements EmailService{

    @Autowired
    private JavaMailSenderImpl javaMailSender;
    @Autowired
    private SmtpPropsConfig smtpPropsConfig;
    private ExecutorService executorService=null;

    @PostConstruct
    public void startEmailWorkers(){
        executorService = Executors.newFixedThreadPool(smtpPropsConfig.getEmailWorkers());
    }

    public void sendMessage(String to, String subject, String text){
        executorService.submit(() -> {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message);
            try {
                messageHelper.setFrom(smtpPropsConfig.getFrom());
                messageHelper.setTo(to);
                messageHelper.setSubject(subject);
                messageHelper.setText(text,true);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            javaMailSender.send(message);
        });
    }

}