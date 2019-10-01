package com.slt.documentmanagment.service;

public interface EmailService {
    boolean sendMessage(String to, String subject, String text);
}
