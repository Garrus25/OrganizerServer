
import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.api.mailer.config.TransportStrategy;
import org.simplejavamail.config.ConfigLoader;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;
import java.util.Objects;


public final class EmailApp {

    private static EmailApp instance = null;
    private final Mailer mailer;

    private EmailApp() {
        this.mailer = MailerBuilder
                .withTransportStrategy(TransportStrategy.SMTP_TLS)
                .async()
                .buildMailer();
    }


    public void sendMail(String emailTo) {
        String subject = "Account verification";
        String token = RandomSequence.getRandomNumberSequence(6);
        String message = "Verification Token: " + token;
        Email email = EmailBuilder.startingBlank()
                .from(Objects.requireNonNull(ConfigLoader.getStringProperty(ConfigLoader.Property.SMTP_USERNAME)))
                .to(emailTo)
                .withSubject(subject)
                .withPlainText(message)
                .buildEmail();
        mailer.sendMail(email);
        mailer.getOperationalConfig()
                .getExecutorService().shutdownNow();
    }

    public static EmailApp getInstance() {
        if (instance == null) {
            instance = new EmailApp();
        }
        return instance;
    }
}

