package com.zenika.mail;

import org.junit.Test;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.subethamail.wiser.Wiser;
import org.subethamail.wiser.WiserMessage;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MailSenderTest {

    private static final int SMTP_PORT = 5555;

    @Test
    public void should_send_mail_to_customer_address() throws MessagingException, IOException {
        Wiser wiser = new Wiser(SMTP_PORT);
        wiser.start();

        new MailSender(getJavaMailSender(), "contact@acme.org").send(
                new CustomerMail("john@acme.org", "Hey you!", "How are you?")
        );

        List<WiserMessage> messages = wiser.getMessages();
        assertThat(messages).hasSize(1);

        MimeMessage mimeMessage = messages.get(0).getMimeMessage();
        assertThat(mimeMessage.getSubject()).isEqualTo("Hey you!");
        assertThat(mimeMessage.getFrom()[0].toString()).isEqualTo("contact@acme.org");
        assertThat(mimeMessage.getRecipients(Message.RecipientType.TO)[0].toString()).isEqualTo("john@acme.org");
        assertThat(mimeMessage.getContent().toString()).isEqualTo("How are you?\r\n");

        wiser.stop();
    }

    private JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost("localhost");
        javaMailSender.setPort(SMTP_PORT);
        javaMailSender.setProtocol("smtp");
        return javaMailSender;
    }
}
