package peaksoft.appplaza.model.dto.MailSender;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@Builder
public class MailResponse {
    private Long id;
    private String text;
    private String subject;
    private LocalDate localDate;
    private String response;
}