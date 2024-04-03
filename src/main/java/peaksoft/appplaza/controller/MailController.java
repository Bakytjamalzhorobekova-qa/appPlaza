package peaksoft.appplaza.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import peaksoft.appplaza.model.dto.MailSender.MailRequest;
import peaksoft.appplaza.model.dto.MailSender.MailResponse;
import peaksoft.appplaza.service.MailService;
import peaksoft.appplaza.service.UserService;


@RestController
@RequestMapping("api/mailing")
@RequiredArgsConstructor
public class MailController {
    private final MailService mailService;

    @PostMapping("/send")
    public ResponseEntity<MailResponse> save(@RequestBody MailRequest request) {
        MailResponse response = mailService.save(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

//    @PutMapping("/forgot-password")
//    public ResponseEntity<String> forgotPassword(@RequestParam String email) throws MessagingException {
//        return  new ResponseEntity<>(userService.forgotPassword(email),HttpStatus.CREATED);
//    }

}
