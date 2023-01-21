package com.user_manager_service.service;

import com.model.EmailDetailsV0;

import java.io.IOException;

public interface EmailJobService {

    boolean sendSimpleMail() throws IOException, ClassNotFoundException;

    String sendMailWithAttachment(EmailDetailsV0 emailDetailsV0);


    Byte[] serializeEmailDetailsVO(EmailDetailsV0 emailDetailsV0) throws IOException;

    EmailDetailsV0 deserializeEmailDetailsVO(Byte[] data) throws IOException, ClassNotFoundException;
}
