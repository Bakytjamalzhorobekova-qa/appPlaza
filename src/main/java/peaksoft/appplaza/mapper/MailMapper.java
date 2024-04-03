package peaksoft.appplaza.mapper;

import org.springframework.stereotype.Component;
import peaksoft.appplaza.model.dto.MailSender.MailRequest;
import peaksoft.appplaza.model.dto.MailSender.MailResponse;
import peaksoft.appplaza.model.dto.genre.GenreResponse;
import peaksoft.appplaza.model.dto.user.RegistrationRequest;
import peaksoft.appplaza.model.entities.Genre;
import peaksoft.appplaza.model.entities.MailSender;
import peaksoft.appplaza.model.entities.User;
import peaksoft.appplaza.model.enums.Status;

import java.time.LocalDate;

@Component
public class MailMapper {
    public MailSender mapToEntity(MailRequest request) {
        MailSender mailSender = new MailSender();
        mailSender.setText(request.getText());
        mailSender.setSubject(request.getSubject());
        mailSender.setLocalDate(LocalDate.now());
        return mailSender;
    }


    public MailResponse mapToResponse(MailSender mailSender) {
        return MailResponse.builder()
                .id(mailSender.getId())
                .text(mailSender.getText())
                .subject(mailSender.getSubject())
                .localDate(mailSender.getLocalDate())
                .response("Message sent successfully")
                .build();
    }
}
