package peaksoft.appplaza.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import peaksoft.appplaza.model.dto.application.ApplicationResponse;
import peaksoft.appplaza.model.dto.application.ApplicationRequest;
import peaksoft.appplaza.model.entities.Application;
import peaksoft.appplaza.model.enums.Status;


import java.time.LocalDate;

@Component //-> класстын объектисин тузу учун керек
@RequiredArgsConstructor
public class ApplicationMapper {
    private final GenreMapper genreMapper;

    public Application mapToEntity(ApplicationRequest request) {
        Application application = new Application();
        application.setName(request.getName());
        application.setDescription(request.getDescription());
        application.setVersion(request.getVersion());
        application.setStatus(request.getAppStatus());
        application.setLocalDate(LocalDate.now());
        return application;


    }

    public ApplicationResponse mapToResponse(Application application) {
        return ApplicationResponse.builder()
                .id(application.getId())
                .name(application.getName())
                .description(application.getDescription())
                .version(application.getVersion())
                .appStatus(application.getStatus())
                .genre(genreMapper.mapToResponse(application.getGenre()))
                .genreName(application.getGenreName())
                .localDate(application.getLocalDate())
                .build();

    }


}
