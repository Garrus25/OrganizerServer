package Services;

import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.api.mailer.config.TransportStrategy;
import org.simplejavamail.config.ConfigLoader;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;
import java.util.Objects;


public final class MailerServices {
    private static Mailer mailer;

    static {
        createMailer();
    }

    public static void sendMail(String emailTo,String token) {
        Email mail= MessageMailTextService.createMail(emailTo,token);
        mailer.sendMail(mail);
        mailer.getOperationalConfig()
                .getExecutorService().shutdownNow();
    }

    private static Mailer createMailer() {
        if (mailer == null) {
            mailer = MailerBuilder
                    .withTransportStrategy(TransportStrategy.SMTP_TLS)
                    .async()
                    .buildMailer();
        }
        return mailer;
    }
}

