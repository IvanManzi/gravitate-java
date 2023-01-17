package com.user_manager_service.service;

import com.model.EmailDetailsV0;

public interface EmailService {

    String sendSimpleMail(EmailDetailsV0 emailDetailsV0);

    String sendMailWithAttachment(EmailDetailsV0 emailDetailsV0);
}
