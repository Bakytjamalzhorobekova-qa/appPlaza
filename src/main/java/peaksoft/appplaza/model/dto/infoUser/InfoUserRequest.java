package peaksoft.appplaza.model.dto.infoUser;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InfoUserRequest {
    private String passportNumber;
    private String address;
    private int cardNumber;
}
