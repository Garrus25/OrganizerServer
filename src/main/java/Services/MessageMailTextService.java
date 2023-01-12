package Services;

import org.simplejavamail.api.email.Email;
import org.simplejavamail.config.ConfigLoader;
import org.simplejavamail.email.EmailBuilder;

import java.util.Objects;

public class MessageMailTextService {
    private static final  String subject = "Account verification";
    private static final  String mailMessage = "Verification Token: ";



    private static String createTextMail(String token){
        String tokenAuthotizeUser=token;// TokenAuthotizeGeneratorService.createTokenAuthorizeUser();

        String message = mailMessage.concat(tokenAuthotizeUser);
        return message;
    }

    public static Email createMail(String emailTo,String token){

        String textMail=createTextMail(token);

        Email email = EmailBuilder.startingBlank()
                .from(Objects.requireNonNull(ConfigLoader.getStringProperty(ConfigLoader.Property.SMTP_USERNAME)))
                .to(emailTo)
                .withSubject(subject)
                .withPlainText(textMail)
                .buildEmail();

        return email;
    }






}
