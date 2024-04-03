package peaksoft.appplaza.model.dto.infoUser;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import peaksoft.appplaza.model.dto.user.UserResponse;
import peaksoft.appplaza.model.entities.User;

@Getter
@Setter
@Builder
public class InfoUserResponse {
    private Long id;
    private UserResponse user;
    private  String passportNumber;
    private String address;
    private int cardNumber;


}
