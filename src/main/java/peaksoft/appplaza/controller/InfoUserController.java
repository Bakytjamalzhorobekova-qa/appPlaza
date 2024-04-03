package peaksoft.appplaza.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import peaksoft.appplaza.model.dto.infoUser.InfoUserRequest;
import peaksoft.appplaza.model.dto.infoUser.InfoUserResponse;
import peaksoft.appplaza.model.entities.InfoUser;
import peaksoft.appplaza.service.InfoUserService;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("api/infoUsers")
@RequiredArgsConstructor
public class InfoUserController {
    private final InfoUserService infoUserService;
    @PostMapping("/add")
    public ResponseEntity<InfoUserResponse> save(@RequestBody InfoUserRequest request, Principal principal){
        InfoUserResponse response =infoUserService.save(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public InfoUserResponse findById(@PathVariable("id")Long id){
        return infoUserService.findById(id);
    }
//    @GetMapping("/userInfo")
//    public List<InfoUserResponse> getUserInfo(Principal principal){
//        return infoUserService.getUser(principal.getName());
//    }

}
