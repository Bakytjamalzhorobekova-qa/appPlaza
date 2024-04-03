package peaksoft.appplaza.service;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import peaksoft.appplaza.mapper.LoginMapper;
import peaksoft.appplaza.mapper.UserMapper;
import peaksoft.appplaza.model.dto.LoginRequest;
import peaksoft.appplaza.model.dto.LoginResponse;
import peaksoft.appplaza.model.dto.user.RegistrationRequest;
import peaksoft.appplaza.model.dto.user.RegistrationResponse;
import peaksoft.appplaza.model.dto.user.UserResponse;
import peaksoft.appplaza.model.entities.Role;
import peaksoft.appplaza.model.entities.User;
import peaksoft.appplaza.model.security.jwt.JwtUtil;
import peaksoft.appplaza.repository.RoleRepository;
import peaksoft.appplaza.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor //-> конструкторду автоматически тузуп внедрение кылат
public class UserService {
    private final UserRepository repository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final LoginMapper loginMapper;
    private final MailService mailService;


    public RegistrationResponse registration(RegistrationRequest request) {
        User user = userMapper.mapToEntity(request);
        if (user.getName().length() < 2 || user.getLastName().length() < 2) {
            throw new RuntimeException("UserName 2 символдон  коп болсун !");
        }
        if (!user.getEmail().contains("@")) {
            throw new RuntimeException("email'де @ символ камтылышы керек");
        }
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        List<Role> roles = new ArrayList<>();
        if (repository.findAll().isEmpty()) {
            Role role = roleRepository.findByName("ADMIN");
            if (role == null) {
                role = new Role("ADMIN");
            }
            roles.add(role);
        } else {
            Role role = roleRepository.findByName("USER");
            if (role == null) {
                role = new Role("USER");
            }
            roles.add(role);
        }
        user.setRoles(roles);
        repository.save(user);
        return userMapper.mapToResponse(user);
    }

    public LoginResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        User user = repository.findByEmail(request.getEmail()).orElseThrow(() -> new RuntimeException("Not found "));
        String jwt = jwtUtil.generateToken(user);
        return loginMapper.matToResponse(jwt, user);
    }

    public List<UserResponse> searchAndPagination(String text, int page, int size) {
        String name = text == null ? "" : text;

        Pageable pageable = PageRequest.of(page - 1, size);
        List<User> users = repository.searchAndPagination(name.toUpperCase(), pageable);
        List<UserResponse> responses = new ArrayList<>();
        for (User user : users) {
            responses.add(userMapper.mapToUserResponse(user));

        }
        return responses;
    }


    public UserResponse findById(Long id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found by id: " + id));
        return userMapper.mapToUserResponse(user);
    }

    public List<UserResponse> findAll() {
        System.out.println("I'm' in find all method in service layer");
        return repository.findAll()
                .stream()
                .map(userMapper::mapToUserResponse).toList();
    }

    public UserResponse update(Long userId, RegistrationRequest request) {
        User oldUser = repository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found by id: " + userId));
        oldUser.setName(request.getName());
        oldUser.setAge(request.getAge());
        oldUser.setEmail(request.getEmail());
        oldUser.setSubscribe(request.isSubscribe());
        oldUser.setLastName(request.getLastName());
        repository.save(oldUser);
        return userMapper.mapToUserResponse(oldUser);
    }

    public void removeUserById(Long userId) {
        User user = repository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found by id: " + userId));
        repository.delete(user);
    }

    public String forgotPassword(String email) {
        User user = repository.findByEmail(email)
                .orElseThrow(
                        () -> new RuntimeException("User not found with this email : " + email));
        Random random = new Random();
        Long resetCod = random.nextLong(10000000);
        user.setResetCode(resetCod);
        repository.save(user);
        try {
            mailService.sendSetPasswordEmail(email);
        } catch (MessagingException e) {
            throw new RuntimeException("User not found with this email : " + email);
        }

        return "Please check your email to set new password to your account";

    }

    public String setPassword(String email, String newPassword) {
        User user = repository.findByEmail(email)
                .orElseThrow(
                        () -> new RuntimeException("User not found with this email : " + email));
        String strCode= String.valueOf(user.getResetCode());
        if (strCode.equals(newPassword)){
            user.setPassword(newPassword);
        }


        repository.save(user);
        return "New password set successfully login with this password";
    }
}












