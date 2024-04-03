package peaksoft.appplaza.service;

import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.appplaza.mapper.InfoUserMapper;
import peaksoft.appplaza.model.dto.infoUser.InfoUserRequest;
import peaksoft.appplaza.model.dto.infoUser.InfoUserResponse;
import peaksoft.appplaza.model.entities.InfoUser;
import peaksoft.appplaza.model.entities.User;
import peaksoft.appplaza.repository.InfoUserRepository;
import peaksoft.appplaza.repository.UserRepository;


import java.util.List;

@Service
@RequiredArgsConstructor
public class InfoUserService {
    private  final InfoUserMapper infoUserMapper;
    private final InfoUserRepository infoUserRepository;
    private  final UserRepository userRepository;

    public InfoUserResponse save(InfoUserRequest request){
        InfoUser infoUser = infoUserMapper.mapToEntity(request);
        infoUserRepository.save(infoUser);
        return infoUserMapper.mapToResponse(infoUser);
    }

    public InfoUserResponse findById(Long id){
        InfoUser infoUser = infoUserRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("InfoUser not found  by id :"+id));
        return infoUserMapper.mapToResponse(infoUser);
    }

    public List<InfoUserResponse> findAll(){
        return infoUserRepository.findAll()
                .stream()
                .map(infoUserMapper::mapToResponse).toList();
    }

    public InfoUserResponse update(Long infoId,InfoUserRequest request){
        InfoUser oldInfoUser = infoUserRepository.findById(infoId)
                .orElseThrow(()-> new RuntimeException("InfoUser not found  by id :" +infoId));
        oldInfoUser.setPassportNumber(request.getPassportNumber());
        oldInfoUser.setPassportNumber(request.getPassportNumber());
        oldInfoUser.setCardNumber(request.getCardNumber());
        infoUserRepository.save(oldInfoUser);
        return infoUserMapper.mapToResponse(oldInfoUser);

    }

    public void removeById(Long infoId){
        InfoUser infoUser = infoUserRepository.findById(infoId).
                orElseThrow(()-> new RuntimeException("InfoUser not found  by id : " +infoId));
        infoUserRepository.delete(infoUser);
    }

//    public List<InfoUserResponse> getUser(String username){
//        User user = userRepository.findByEmail(username).get();
//      List<InfoUser> infoUsers = infoUserRepository.findAll().stream().toList();
//      return infoUsers
//              .stream()
//              .map(infoUserMapper::mapToResponse).toList();
//    }


}
