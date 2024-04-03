package peaksoft.appplaza.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.*;
import peaksoft.appplaza.model.dto.application.ApplicationResponse;
import peaksoft.appplaza.model.dto.application.ApplicationRequest;
import peaksoft.appplaza.service.ApplicationService;
import peaksoft.appplaza.service.UserService;

import java.security.Principal;
import java.util.List;

@RestController //-> Json формат кайтарат
@RequestMapping("api/apps") //-> url беребиз
@RequiredArgsConstructor // -> конструкторду автоматически тузуп внедрение кылат
public class ApplicationController {
    private final ApplicationService applicationService;

    @PostMapping("/add")
    public ResponseEntity<ApplicationResponse> save(@RequestBody ApplicationRequest request) {
        ApplicationResponse response = applicationService.save(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ApplicationResponse findById(@PathVariable("id") Long id) {
        return applicationService.findById(id);
    }

    @GetMapping()
    public List<ApplicationResponse> findAll() {
        return applicationService.findAll();
    }

    @PutMapping("update/{id}")
    public ApplicationResponse update(@PathVariable("id") Long id, @RequestBody ApplicationRequest request) {
        return applicationService.update(id, request);
    }

    @DeleteMapping("delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        applicationService.removeById(id);
        return "Application with id " + id + " successfully deleted";
    }

    @GetMapping("/search")
    public List<ApplicationResponse> searchApplication(@RequestParam(name = "text", required = false) String text,
                                                       @RequestParam int page,
                                                       @RequestParam int size) {
        return applicationService.searchApplication(text, page, size);

    }

    @PostMapping("/download/{id}")
    public List<ApplicationResponse> download(@PathVariable("id") Long appId, Principal principal) {
        return applicationService.download(appId, principal.getName());
    }

    @GetMapping("/my-applications")
    public List<ApplicationResponse> getUserApplication(Principal principal) {
        return applicationService.myApplication(principal.getName());
    }

@GetMapping("/users-by-status")
    public  List<ApplicationResponse> getUserApplicationByStatus(Principal principal) {
    return applicationService.appUserByStatus(principal.getName());


}
}