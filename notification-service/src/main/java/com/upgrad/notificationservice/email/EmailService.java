package com.upgrad.notificationservice.email;

import com.upgrad.notificationservice.model.MailEntity;
import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public interface EmailService {

    public void init();

    public void sendEmail(MailEntity mailEntity) throws IOException, TemplateException, MessagingException;

    public void sendSimpleMessage(MailEntity mailEntity) throws MessagingException;

    public void verifyEmail(String emailId);
}
