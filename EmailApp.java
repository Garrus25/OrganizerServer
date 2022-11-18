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
                            <h1>TEST EMAIL PWJ</h1> 
                            <h2>Zapraszamy do projektu.</h2>                                 
                        </body>
                    </html>
                """;

        final var USERNAME = "hgalanty@op.pl";
        final var PASSWORD = "";
        final var TO_EMAIL = "rgalanty@op.pl";
        final var FROM_EMAIL_DESCRIPTION = "hgalanty@op.pl";
        final var TO_EMAIL_DESCRIPTION = "rgalanty@op.pl";
        final var SMTP_HOST = "smtp.poczta.onet.pl";
        final var PORT = 465; // serwer poczty wychodzącej, port, na którym działa SMTP, domena op.pl


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

