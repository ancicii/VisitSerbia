package sbnz.visitserbia.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import sbnz.visitserbia.model.RegisteredUser;
import sbnz.visitserbia.model.VerificationToken;


@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String emailAddress;

    @Async
    public void sendVerificationEmail(RegisteredUser registeredUser, String url, VerificationToken verificationToken) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(registeredUser.getEmail());
        mailMessage.setSubject("Complete registration");
        mailMessage.setFrom(emailAddress);
        mailMessage.setText(String.format("Dear %s %s,%nTo confirm your account please click here: %n"
                + "http://" + url + "/api/verify-account?token=" + verificationToken.getToken(), registeredUser.getFirstName(), registeredUser.getLastName()));
        javaMailSender.send(mailMessage);

    }

}
