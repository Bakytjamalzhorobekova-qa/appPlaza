package peaksoft.appplaza.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import peaksoft.appplaza.model.dto.genre.GenreResponse;
import peaksoft.appplaza.model.dto.genre.GenreRequest;
import peaksoft.appplaza.service.GenreService;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("api/genres")
@RequiredArgsConstructor  //->  конструкторду автоматически тузуп внедрение кылат
public class GenreController {
    private final GenreService genreService;

    @PostMapping("/add")
    public ResponseEntity<GenreResponse> save(@RequestBody GenreRequest request) {
        GenreResponse response = genreService.save(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public GenreResponse findById(@PathVariable Long id, Principal principal) {
        System.out.println(principal.getName());
        return genreService.findById(id);
    }

    @GetMapping()
    public List<GenreResponse> findAll() {
        return genreService.findAll();
    }

    @PutMapping("update/{id}")
    public GenreResponse update(@PathVariable("id") Long id, @RequestBody GenreRequest request) {
        return genreService.update(id, request);
    }

    @DeleteMapping("delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        genreService.removeById(id);
        return "Genre with id " + id + " successfully deleted";
    }

    @GetMapping("/search")
    public List<GenreResponse> searchAndPagination(@RequestParam(name = "text", required = false) String text,
                                                   @RequestParam int page,
                                                   @RequestParam int size) {
        return genreService.searchAndPagination(text, page, size);
    }


}

