package com.prudential.demo.service;

import java.util.Map;

public interface MailService {
    public String sendMail(Map<String, Object> properties, String receiver, String attachment);
}
