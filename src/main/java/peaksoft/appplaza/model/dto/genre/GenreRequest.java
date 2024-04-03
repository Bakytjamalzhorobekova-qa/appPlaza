package peaksoft.appplaza.model.dto.genre;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenreRequest {
    private String name;
    private String description;
}
