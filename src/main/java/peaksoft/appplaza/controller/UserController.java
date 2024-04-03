package peaksoft.appplaza.controller;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import peaksoft.appplaza.model.dto.LoginRequest;
import peaksoft.appplaza.model.dto.LoginResponse;
import peaksoft.appplaza.model.dto.user.RegistrationRequest;
import peaksoft.appplaza.model.dto.user.RegistrationResponse;
import peaksoft.appplaza.model.dto.user.UserResponse;
import peaksoft.appplaza.model.entities.InfoUser;
import peaksoft.appplaza.model.entities.User;
import peaksoft.appplaza.repository.UserRepository;
import peaksoft.appplaza.service.InfoUserService;
import peaksoft.appplaza.service.MailService;
import peaksoft.appplaza.service.UserService;

import java.security.Principal;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor //-> конструкторду автоматически тузуп внедрение кылат
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;
    private final MailService mailService;

    @PostMapping("/sign-up")
    public ResponseEntity<RegistrationResponse> registration(@RequestBody RegistrationRequest request) {
        RegistrationResponse response = userService.registration(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/sign-in")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return userService.login(request);
    }

    @GetMapping("/{id}")
    public UserResponse findById(@PathVariable Long id, Principal principal) {
        System.out.println(principal.getName());
        return userService.findById(id);

    }

    @GetMapping()
    public List<UserResponse> findAll() {

        return userService.findAll();
    }

    @PutMapping("update/{id}")
    public UserResponse update(@PathVariable("id") Long id, @RequestBody RegistrationRequest request) {
        return userService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.removeUserById(id);
        return "User with id " + id + " successfully deleted";
    }

    @GetMapping("/search")
    public List<UserResponse> searchAndPagination(@RequestParam(name = "text", required = false)
                                                  String text, @RequestParam int page,
                                                  @RequestParam int size) {
        return userService.searchAndPagination(text, page, size);
    }

    @PutMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestParam String email){
        User user = userRepository.findByEmail(email)
                .orElseThrow(()-> new RuntimeException("User not found with this email : "+email));
        userRepository.save(user);
        return  new ResponseEntity<>(userService.forgotPassword(email),HttpStatus.CREATED);
    }
    @PutMapping("/set-password")
    public ResponseEntity<String> setPassword(@RequestParam String email,@RequestHeader String newPassword){
        User user = userRepository.findByEmail(email)
                .orElseThrow(()-> new RuntimeException("User not found with this email : "+email));
        userRepository.save(user);

        return  new ResponseEntity<>(userService.setPassword(email,newPassword),HttpStatus.CREATED);
    }






}





