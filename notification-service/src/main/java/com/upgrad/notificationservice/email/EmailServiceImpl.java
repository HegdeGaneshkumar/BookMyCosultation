package com.upgrad.notificationservice.email;

import com.upgrad.notificationservice.model.MailEntity;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import jakarta.annotation.PostConstruct;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ses.SesClient;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@RequiredArgsConstructor
@Component
public class EmailServiceImpl implements EmailService{

    private SesClient sesClient;
    private final FreeMarkerConfigurer configurer;
    private String fromEmail = "";
    private String accessKey;
    private String secretKey;

    public void initVerify(){
        accessKey="***REMOVED***";
        secretKey="***REMOVED***";
        StaticCredentialsProvider staticCredentialsProvider = StaticCredentialsProvider
                .create(AwsBasicCredentials.create(accessKey,secretKey));
        sesClient = SesClient.builder()
                .credentialsProvider(staticCredentialsProvider)
                .region(Region.US_EAST_1)
                .build();
    }

    @Override
    public void init() {
        accessKey="AKIA4JO4AH56G2V6OAO3";
        secretKey="BKFRw7/txnXmAkxSGRfnpWn5YmcDAv7AF6LT0CyfpDup";//
        StaticCredentialsProvider staticCredentialsProvider = StaticCredentialsProvider
                .create(AwsBasicCredentials.create(accessKey,secretKey));
        sesClient = SesClient.builder()
                .credentialsProvider(staticCredentialsProvider)
                .region(Region.US_EAST_1)
                .build();
    }

    @Override
    public void sendEmail(MailEntity mailEntity) throws IOException, TemplateException, MessagingException {
        Map<String,Object> templateModel = new HashMap<>();
        templateModel.put("MailEntity",mailEntity);
        //Template freeMarkerTemplate = configurer.getConfiguration().getTemplate("useremail.ftl");
        //String htmlBody = FreeMarkerTemplateUtils.processTemplateIntoString(freeMarkerTemplate,templateModel);
        sendSimpleMessage(mailEntity);
    }

    @Override
    public void sendSimpleMessage(MailEntity mailEntity) throws MessagingException {
        Properties props = System.getProperties();
        props.put("mail.transport.protocol","smtp");
        props.put("mail.smtp.port",587);
        props.put("mail.smtp.starttls.enable","true");
        props.put("mail.smtp.auth","true");
        Session session = Session.getDefaultInstance(props);
        MimeMessage msg = new MimeMessage(session);
        msg.setFrom(fromEmail);
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(mailEntity.getEmailId()));
        msg.setSubject(mailEntity.getSubject());
        msg.setContent(mailEntity.getMessage(),"text/html");
        Transport transport = session.getTransport();
        try {
            transport.connect("email-smtp.us-east-1.amazonaws.com", accessKey, secretKey);
            transport.sendMessage(msg, msg.getAllRecipients());
        }catch(Exception e){
            System.out.println(e.getMessage());
        }finally {
            transport.close();
        }
    }

    @Override
    public void verifyEmail(String emailId) {
        sesClient.verifyEmailAddress(req->req.emailAddress(emailId));
    }
}
