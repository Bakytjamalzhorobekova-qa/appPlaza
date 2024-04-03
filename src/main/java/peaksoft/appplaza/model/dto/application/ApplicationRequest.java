package peaksoft.appplaza.model.dto.application;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import peaksoft.appplaza.model.dto.genre.GenreResponse;
import peaksoft.appplaza.model.enums.Status;

import java.time.LocalDate;

@Getter //-> значение алуу учун
@Setter //-> значение беруу учун
public class ApplicationRequest {       // http запрос user жактан берилген запрос
    private String name;
    private String description;
    private String version;
    private Status appStatus;
    private LocalDate localDate;
    private Long genreId;

}
