package com.zenika.mail;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

public class MailSender {

    private final JavaMailSender javaMailSender;
    private final String fromAddress;

    public MailSender(JavaMailSender javaMailSender, String fromAddress) {
        this.fromAddress = fromAddress;
        this.javaMailSender = javaMailSender;
    }

    public void send(CustomerMail customerMail) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");

        try {
            helper.setFrom(fromAddress);
            helper.setTo(customerMail.getToAddress());
            helper.setSubject(customerMail.getSubject());
            helper.setText(customerMail.getText());

            javaMailSender.send(helper.getMimeMessage());
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
