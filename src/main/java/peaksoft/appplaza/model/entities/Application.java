package peaksoft.appplaza.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import peaksoft.appplaza.model.enums.Status;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "applications")
@Getter
@Setter
@NoArgsConstructor
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String version;
    @Enumerated(EnumType.STRING)
    private Status status;
    private String GenreName;
    private LocalDate localDate;

    //Отношение applocations and users
    @ManyToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.PERSIST},mappedBy = "applications")
    private List<User> users;
    //Отношение users and genre
    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST})
    @JoinColumn(name = "genre_id")
    private Genre genre;



}
