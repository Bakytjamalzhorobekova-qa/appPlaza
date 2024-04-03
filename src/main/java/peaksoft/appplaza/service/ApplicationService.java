package peaksoft.appplaza.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;
import peaksoft.appplaza.mapper.ApplicationMapper;
import peaksoft.appplaza.mapper.UserMapper;
import peaksoft.appplaza.model.dto.application.ApplicationResponse;
import peaksoft.appplaza.model.dto.application.ApplicationRequest;
import peaksoft.appplaza.model.entities.Application;
import peaksoft.appplaza.model.entities.Genre;
import peaksoft.appplaza.model.entities.User;
import peaksoft.appplaza.model.enums.Status;
import peaksoft.appplaza.repository.ApplicationRepository;
import peaksoft.appplaza.repository.GenreRepository;
import peaksoft.appplaza.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationService {
    private final ApplicationMapper applicationMapper;
    private final ApplicationRepository applicationRepository;
    private final GenreRepository genreRepository;
    private final UserRepository userRepository;


    public ApplicationResponse save(ApplicationRequest request) {
        Application application = applicationMapper.mapToEntity(request);
        Genre genre = genreRepository.findById(request.getGenreId()).get();
        if (application.getName().length() < 2) {
            throw new RuntimeException("ApplicationName  must be more than 2 characters");
        }
        application.setGenre(genre);
        application.setGenreName(genre.getName());
        applicationRepository.save(application);
        return applicationMapper.mapToResponse(application);
    }

    public List<ApplicationResponse> searchApplication(String text, int page, int size) {
        String name = text == null ? "" : text;
        Pageable pageable = PageRequest.of(page - 1, size);
        List<Application> applications = applicationRepository.searchApplication(name.toUpperCase(), pageable);
        List<ApplicationResponse> responses = new ArrayList<>();
        for (Application application : applications) {
            responses.add(applicationMapper.mapToResponse(application));

        }
        return responses;
    }

    public ApplicationResponse findById(Long id) {
        Application application = applicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Application with this id not found " + id));
        return applicationMapper.mapToResponse(application);
    }

    public List<ApplicationResponse> findAll() {
        return applicationRepository.findAll()
                .stream().map(applicationMapper::mapToResponse).toList();
    }

    public ApplicationResponse update(Long appId, ApplicationRequest request) {
        Application oldApp = applicationRepository.findById(appId)
                .orElseThrow(() -> new RuntimeException("Application with this id not found " + appId));
        Genre genre = genreRepository.findById(request.getGenreId()).get();
        oldApp.setName(request.getName());
        oldApp.setDescription(request.getDescription());
        oldApp.setVersion(request.getVersion());
        oldApp.setGenre(genre);
        oldApp.setGenreName(genre.getName());
        applicationRepository.save(oldApp);
        return applicationMapper.mapToResponse(oldApp);
    }

    public void removeById(Long appId) {
        Application application = applicationRepository.findById(appId)
                .orElseThrow(() -> new RuntimeException("Application with this id not found " + appId));
        applicationRepository.delete(application);
    }

    public List<ApplicationResponse> download(Long appId, String username) {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("Not found user with name: " + username));
        Application application = applicationRepository.findById(appId)
                .orElseThrow(() -> new RuntimeException("Not found application with: " + appId));
        List<Application> myApplications = user.getApplications();
        if (!myApplications.contains(application)) {
            myApplications.add(application);
        }
        userRepository.save(user);

        return myApplication(user.getUsername());

    }

    public List<ApplicationResponse> myApplication(String username) {
        User user = userRepository.findByEmail(username).
                orElseThrow(() -> new RuntimeException("Not found user with name" + username));
        List<Application> applications = applicationRepository.myGetApplicationsByUserId(user.getId());
        return applications
                .stream()
                .map(application -> applicationMapper.mapToResponse(application)).toList();

    }

    public List<ApplicationResponse> appUserByStatus(String username) {
        User user = userRepository.findByEmail(username).get();
        List<Application> applications = applicationRepository.myGetApplicationsByUserId(user.getId());
        List<Application> basics = applicationRepository.findAllByStatusBasic();
        if (user.getStatus() == Status.BASIC) {
          return basics
                  .stream()
                  .map(applicationMapper::mapToResponse).toList();
        }
        return applications
                .stream()
                .map(applicationMapper::mapToResponse).toList();

    }
}


