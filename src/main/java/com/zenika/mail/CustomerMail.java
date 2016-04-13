package com.zenika.mail;

public class CustomerMail {
    private final String toAddress;
    private final String subject;
    private final String text;

    public CustomerMail(String toAddress, String subject, String text) {
        this.toAddress = toAddress;
        this.subject = subject;
        this.text = text;
    }

    public String getToAddress() {
        return toAddress;
    }

    public String getSubject() {
        return subject;
    }

    public String getText() {
        return text;
    }
}
