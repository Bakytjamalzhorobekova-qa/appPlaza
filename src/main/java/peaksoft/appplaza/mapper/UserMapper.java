package peaksoft.appplaza.mapper;

import org.springframework.stereotype.Component;
import peaksoft.appplaza.model.dto.user.RegistrationRequest;
import peaksoft.appplaza.model.dto.user.RegistrationResponse;
import peaksoft.appplaza.model.dto.user.UserResponse;
import peaksoft.appplaza.model.entities.User;
import peaksoft.appplaza.model.enums.Status;

import java.time.LocalDate;

@Component // -> класстын объектсин учун керек
public class UserMapper {
    public User mapToEntity(RegistrationRequest request) {
        User user = new User();
        user.setName(request.getName());
        user.setLastName(request.getLastName());
        user.setAge(request.getAge());
        user.setEmail(request.getEmail());
        user.setSubscribe(request.isSubscribe());
        user.setStatus(request.getStatus());
        user.setLocalDate(LocalDate.now());
        return user;

    }

    public RegistrationResponse mapToResponse(User user) {
        return RegistrationResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .age(user.getAge())
                .status(user.getStatus())
                .response("Successful Registered").build();
    }

    public UserResponse mapToUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .lastName(user.getLastName())
                .age(user.getAge())
                .subscribe(user.isSubscribe())
                .status(user.getStatus())
                .localDate(user.getLocalDate()).build();
    }


}
