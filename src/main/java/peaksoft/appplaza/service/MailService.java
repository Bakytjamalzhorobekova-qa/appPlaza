package peaksoft.appplaza.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import peaksoft.appplaza.mapper.MailMapper;
import peaksoft.appplaza.mapper.UserMapper;
import peaksoft.appplaza.model.dto.MailSender.MailRequest;
import peaksoft.appplaza.model.dto.MailSender.MailResponse;
import peaksoft.appplaza.model.dto.user.UserResponse;
import peaksoft.appplaza.model.entities.MailSender;
import peaksoft.appplaza.model.entities.User;
import peaksoft.appplaza.repository.MailRepository;
import peaksoft.appplaza.repository.UserRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MailService {
    private final JavaMailSender javaMailSender;
    private final UserRepository userRepository;
    private  final MailMapper mailMapper;
    private final UserMapper userMapper;
    private  final MailRepository mailRepository;

    public MailResponse save(MailRequest request) {
        MailSender mailSender = mailMapper.mapToEntity(request);
        List<User> users = userRepository.findAll();
        List<UserResponse> responses = new ArrayList<>();
        for (User user : users) {
            sendMassage(mailSender, user.getEmail());
            responses.add(userMapper.mapToUserResponse(user));
        }
        request.setLocalDate(LocalDate.now());
        mailRepository.save(mailSender);
        return mailMapper.mapToResponse(mailSender);
    }

    public void sendMassage(MailSender mailSender, String email) {
        SimpleMailMessage massage = new SimpleMailMessage();
        massage.setTo(email);
        massage.setFrom("AppPlaza");
        massage.setSubject(mailSender.getSubject());
        massage.setText(mailSender.getText());
        javaMailSender.send(massage);
    }

    public void sendSetPasswordEmail(String email) throws MessagingException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with this email : " + email));

        String strCode = String.valueOf(user.getResetCode());
        userRepository.save(user);
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        mimeMessageHelper.setTo(email);
        mimeMessageHelper.setSubject("Set Password");
        mimeMessageHelper.setText("Your reset code: " + strCode);
        javaMailSender.send(mimeMessage);
    }


}
