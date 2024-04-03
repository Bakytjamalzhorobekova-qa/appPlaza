package peaksoft.appplaza.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import peaksoft.appplaza.model.dto.genre.GenreResponse;
import peaksoft.appplaza.model.dto.genre.GenreRequest;
import peaksoft.appplaza.model.entities.Genre;
import peaksoft.appplaza.model.enums.Status;

import java.time.LocalDate;

@Component //-> класстын объектисин тузу учун керек
public class GenreMapper {
    public Genre mapToEntity(GenreRequest request) {
        Genre genre = new Genre();
        genre.setName(request.getName());
        genre.setDescription(request.getDescription());
        genre.setLocalDate(LocalDate.now());
        return genre;
    }

    public GenreResponse mapToResponse(Genre genre) {
        return GenreResponse.builder()
                .id(genre.getId())
                .name(genre.getName())
                .description(genre.getDescription())
                .localDate(genre.getLocalDate())
                .build();

    }


}



