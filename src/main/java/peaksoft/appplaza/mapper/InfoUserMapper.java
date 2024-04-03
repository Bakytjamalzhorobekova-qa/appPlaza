package peaksoft.appplaza.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import peaksoft.appplaza.model.dto.infoUser.InfoUserRequest;
import peaksoft.appplaza.model.dto.infoUser.InfoUserResponse;
import peaksoft.appplaza.model.entities.InfoUser;
import peaksoft.appplaza.model.entities.User;
import peaksoft.appplaza.repository.UserRepository;

@Component
@RequiredArgsConstructor
public class InfoUserMapper {
    private  final UserMapper userMapper;

    public InfoUser mapToEntity(InfoUserRequest request){
        InfoUser infoUser = new InfoUser();
        infoUser.setPassportNumber(request.getPassportNumber());
        infoUser.setAddress(request.getAddress());
        infoUser.setCardNumber(request.getCardNumber());
        return infoUser;
    }

    public InfoUserResponse mapToResponse(InfoUser infoUser){
        User user = new User();
        return InfoUserResponse.builder()
                .id(infoUser.getId())
                .passportNumber(infoUser.getPassportNumber())
                .address(infoUser.getAddress())
                .cardNumber(infoUser.getCardNumber())
                .user(userMapper.mapToUserResponse(user))
                .build();
    }
}
