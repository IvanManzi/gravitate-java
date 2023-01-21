package com.user_manager_service.service.impl;


import com.model.EmailDetailsV0;
import com.model.JobVO;
import com.user_manager_service.dao.JobDao;
import com.user_manager_service.dao.UserDao;
import com.user_manager_service.service.EmailJobService;
import com.util.ValidationUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.util.List;

@Service
@Slf4j
public class EmailJobServiceImpl implements EmailJobService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private UserDao userDao;

    @Autowired
    private JobDao jobDao;

    @Value("${spring.mail.username}")
    private String sender;

    @Scheduled(fixedRate = 180000)
    @Async
    public boolean sendSimpleMail() throws IOException, ClassNotFoundException {
        List<JobVO> jobs = jobDao.getAllJobs();
        if(ValidationUtil.isNullObject(jobs) || jobs.isEmpty()){
            return true;
        }
        for(JobVO job : jobs){
            EmailDetailsV0 emailDetailsV0 = deserializeEmailDetailsVO(job.getData());
            String emailBody = "Dear " + emailDetailsV0.getRecipientFirstName() + ", \n\n" +
                    "You are now officially a member of the gravitate team. Please use the following credentials to set up your account.\n\n" +
                    "email: " +emailDetailsV0.getRecipient() + "\n"+
                    "password: " + emailDetailsV0.getPassword();
            emailDetailsV0.setMsgBody(emailBody);
            try {
                SimpleMailMessage mailMessage = new SimpleMailMessage();
                mailMessage.setFrom(sender);
                mailMessage.setTo(emailDetailsV0.getRecipient());
                mailMessage.setText(emailDetailsV0.getMsgBody());
                mailMessage.setSubject(emailDetailsV0.getSubject());
                jobDao.removeJob(job.getJobId());
                javaMailSender.send(mailMessage);
                log.info("Sending Mail Success.");
            } catch (Exception e) {
                //log the exception
                log.info("Sending Mail fail");
            }
        }

        return true;


    }


    @Override
    public Byte[] serializeEmailDetailsVO(EmailDetailsV0 emailDetailsVO) throws IOException {
        JobVO jobVO = new JobVO();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(emailDetailsVO);
        oos.flush();
        oos.close();
        byte[] bytes = baos.toByteArray();
        Byte[] data = new Byte[bytes.length];
        int i = 0;
        for (byte b : bytes) data[i++] = b;
        //set Byte[]
        jobVO.setData(data);
        //persist job
        jobDao.createJob(jobVO);
        return data;
    }


    @Override
    public EmailDetailsV0 deserializeEmailDetailsVO(Byte[] data) throws IOException, ClassNotFoundException {
        byte[] bytes = new byte[data.length];
        int i = 0;
        for (Byte b : data) bytes[i++] = b;
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bais);
        return (EmailDetailsV0) ois.readObject();
    }




    @Override
    public String sendMailWithAttachment(EmailDetailsV0 emailDetailsV0) {

        MimeMessage mimeMessage
                = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;

        try {
            mimeMessageHelper
                    = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(emailDetailsV0.getRecipient());
            mimeMessageHelper.setText(emailDetailsV0.getMsgBody());
            mimeMessageHelper.setSubject(
                    emailDetailsV0.getSubject());

            // Adding  attachment
            FileSystemResource file
                    = new FileSystemResource(
                    new File(emailDetailsV0.getAttachment()));

            mimeMessageHelper.addAttachment(
                    file.getFilename(), file);

            javaMailSender.send(mimeMessage);
            System.out.println("sent");
            return "Mail sent Successfully";
        }
        catch (MessagingException e) {
            System.out.println("not sent");
            return "Error while sending mail!!!";
        }
    }
}
