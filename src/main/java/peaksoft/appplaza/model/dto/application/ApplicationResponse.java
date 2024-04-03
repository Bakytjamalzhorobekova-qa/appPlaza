package peaksoft.appplaza.model.dto.application;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import peaksoft.appplaza.model.dto.genre.GenreResponse;
import peaksoft.appplaza.model.entities.Genre;
import peaksoft.appplaza.model.entities.Role;
import peaksoft.appplaza.model.enums.Status;

import java.time.LocalDate;

@Getter  //-> значение алуу учун
@Setter //-> значение беруу учун
@Builder
public class ApplicationResponse {      // http ответ
    private Long id;
    private String name;
    private String description;
    private String version;
    private Status appStatus;
    private String genreName;
    private GenreResponse genre;
    private LocalDate localDate;

}
