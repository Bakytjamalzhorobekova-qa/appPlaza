package peaksoft.appplaza.model.dto.genre;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import peaksoft.appplaza.model.enums.Status;

import java.time.LocalDate;
@Getter
@Setter
@Builder
public class GenreResponse {
    private Long id;
    private String name;
    private String description;
    private LocalDate localDate;

}
