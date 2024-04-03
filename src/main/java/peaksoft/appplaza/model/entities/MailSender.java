package peaksoft.appplaza.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "mailSenders")
@Getter
@Setter
@NoArgsConstructor
public class MailSender {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String subject;
    private String text;
    private LocalDate localDate;
}
