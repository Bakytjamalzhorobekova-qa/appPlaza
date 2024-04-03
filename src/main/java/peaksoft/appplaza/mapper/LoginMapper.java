package peaksoft.appplaza.mapper;

import org.springframework.stereotype.Component;
import peaksoft.appplaza.model.dto.LoginResponse;
import peaksoft.appplaza.model.entities.Role;
import peaksoft.appplaza.model.entities.User;

import java.util.ArrayList;
import java.util.List;

@Component //-> класстын объектисин тузу учун керек
public class LoginMapper {

//    public LoginResponse mapToResponse(String token,String roleName){
//        return LoginResponse.builder()
//                .token(token)
//                .roleName(roleName)
//                .build();
//    }

    public LoginResponse matToResponse(String token, User user) {
        List<String> roles = new ArrayList<>();
        for (Role role : user.getRoles()) {
            roles.add(role.getName());
        }
        return LoginResponse.builder()
                .token(token)
                .roleName(roles)
                .build();
    }
}
