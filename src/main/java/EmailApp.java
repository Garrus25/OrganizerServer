package com.app;

import org.simplejavamail.api.email.Email;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;

public class EmailApp {
    public static void main(String[] args) {

        final var SUBJECT = "Test Email Message";
        final var HTML = """
                    <!DOCTYPE html>
                    <html>
                        <head>
                            <title>Page Title</title>
                        </head>
                        <body>                        
                            <h1>KM Programs Kursy Programowania</h1> 
                            <h2>Zapraszam na szkolenie Java od podstaw</h2>                                 
                        </body>
                    </html>
                """;

        final var USERNAME = "testowy.kmprograms@gmail.com";
        final var PASSWORD = "";
        final var TO_EMAIL = "programowanie.krzysiek@gmail.com";
        final var FROM_EMAIL_DESCRIPTION = "KM Programs";
        final var TO_EMAIL_DESCRIPTION = "Krzysztof";
        final var SMTP_HOST = "smtp.gmail.com";
        final var PORT = 587;

        Email email = EmailBuilder.startingBlank()
                .from(FROM_EMAIL_DESCRIPTION, USERNAME)
                .to(TO_EMAIL_DESCRIPTION, TO_EMAIL)
                .withSubject(SUBJECT)
                .withHTMLText(HTML)
                .buildEmail();

        MailerBuilder
                .withSMTPServer(SMTP_HOST, PORT, USERNAME, PASSWORD)
                .async()
                .buildMailer()
                .sendMail(email);

        System.out.println("DONE!");
    }
}

