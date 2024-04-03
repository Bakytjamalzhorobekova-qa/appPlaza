package peaksoft.appplaza.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "infoUsers")
@Getter
@Setter
@NoArgsConstructor
public class InfoUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String passportNumber;
    private String address;
    private int cardNumber;
    @JsonIgnore
    @OneToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    private User user;
}
