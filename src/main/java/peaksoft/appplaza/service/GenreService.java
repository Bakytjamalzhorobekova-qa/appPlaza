package peaksoft.appplaza.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import peaksoft.appplaza.mapper.GenreMapper;
import peaksoft.appplaza.model.dto.genre.GenreResponse;
import peaksoft.appplaza.model.dto.genre.GenreRequest;
import peaksoft.appplaza.model.entities.Genre;
import peaksoft.appplaza.repository.GenreRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreService {
    private final GenreMapper genreMapper;
    private final GenreRepository repository;

    public GenreResponse save(GenreRequest request) {
        Genre genre = genreMapper.mapToEntity(request);
        if (genre.getName().length() < 2) {
            throw new RuntimeException("GenreName must be more than 2 characters");
        }
        repository.save(genre);
        return genreMapper.mapToResponse(genre);
    }

    public List<GenreResponse> searchAndPagination(String text, int page, int size) {
        String name = text == null ? "" : text;
        Pageable pageable = PageRequest.of(page - 1, size);
        List<Genre> genres = repository.searchAndPagination(name.toUpperCase(), pageable);
        List<GenreResponse> responses = new ArrayList<>();
        for (Genre genre : genres) {
            responses.add(genreMapper.mapToResponse(genre));

        }
        return responses;
    }

    public GenreResponse findById(Long id) {
        Genre genre = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Genre  with this id not found " + id));
        return genreMapper.mapToResponse(genre);
    }

    public List<GenreResponse> findAll() {
        return repository.findAll()
                .stream().map(genreMapper::mapToResponse).toList();
    }

    public GenreResponse update(Long genreId, GenreRequest request) {
        Genre oldGenre = repository.findById(genreId)
                .orElseThrow(() -> new RuntimeException("Genre  with this id not found " + genreId));
        oldGenre.setName(request.getName());
        oldGenre.setDescription(request.getDescription());
        repository.save(oldGenre);
        return genreMapper.mapToResponse(oldGenre);
    }

    public void removeById(Long genreId) {
        Genre genre = repository.findById(genreId)
                .orElseThrow(() -> new RuntimeException("Genre  with this id not found " + genreId));
        repository.delete(genre);

    }

}
