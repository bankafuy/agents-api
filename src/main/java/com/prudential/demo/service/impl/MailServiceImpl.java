package com.prudential.demo.service.impl;

import com.prudential.demo.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.util.ByteArrayDataSource;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

import static com.prudential.demo.AliasName.*;

@Service
public class MailServiceImpl implements MailService {
    private static Logger LOGGER = LoggerFactory.getLogger(MailServiceImpl.class);

    @Override
    public String sendMail(Map<String, Object> mapProperties, String email, String attachment) {
        final String mailSender = mapProperties.get(MAIL_USERNAME).toString();
        final String passwordSender = mapProperties.get(MAIL_PASSWORD).toString();

        Properties properties = new Properties();
        properties.setProperty(MAIL_SMTP_HOST, mapProperties.get(MAIL_SMTP_HOST).toString());
        properties.setProperty(MAIL_SMTP_PORT, mapProperties.get(MAIL_SMTP_PORT).toString());
        properties.setProperty(MAIL_SMTP_SSL_ENABLE, mapProperties.get(MAIL_SMTP_SSL_ENABLE).toString());
        properties.setProperty(MAIL_SMTP_AUTH, mapProperties.get(MAIL_SMTP_AUTH).toString());


        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mailSender, passwordSender);
            }
        });

        try {
            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress(mailSender));
            mimeMessage.setRecipients(Message.RecipientType.TO, new InternetAddress[]{new InternetAddress(email)});
            mimeMessage.setSubject("Report");

            // Body Message
            BodyPart bodyPart = new MimeBodyPart();
            bodyPart.setText(String.format("Hello, %s this is your requested data.", email));

            final byte[] attachmentBytes = Base64.getDecoder().decode(attachment);

            // Attachment
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            final String fileName = formatter.format(new Date()).concat(".xlsx");

            DataSource dataSource = new ByteArrayDataSource(attachmentBytes, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setDataHandler(new DataHandler(dataSource));
            mimeBodyPart.setFileName(fileName);

            // Merge Body and Attachment
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(bodyPart);
            multipart.addBodyPart(mimeBodyPart);

            // Set Content Email
            mimeMessage.setContent(multipart);

            LOGGER.info("Sending Message...");

            Transport.send(mimeMessage);

            LOGGER.info("Message sent.");


        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
