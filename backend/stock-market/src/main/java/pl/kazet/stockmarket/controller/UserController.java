package pl.kazet.stockmarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kazet.stockmarket.dto.user.UserDTO;
import pl.kazet.stockmarket.entity.tables.User;
import pl.kazet.stockmarket.repository.UserRepository;
import pl.kazet.stockmarket.security.WebSecurityConfig;
import pl.kazet.stockmarket.utils.UserUtils;
import pl.kazet.stockmarket.viewmodel.registration.UserCredentialsForm;

import java.util.HashMap;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    UserRepository userRepository;
    WebSecurityConfig webSecurityConfig;

    @Autowired
    public UserController(UserRepository userRepository, WebSecurityConfig webSecurityConfig) {
        this.userRepository = userRepository;
        this.webSecurityConfig = webSecurityConfig;
    }

    @PostMapping(value = "/register")
    public ResponseEntity<?> registerUser(@RequestBody UserCredentialsForm userCredentialsForm) {
        try {
            UserDTO user = new UserDTO();

            user.setEmail(userCredentialsForm.getEmail());
            user.setPassword(webSecurityConfig.passwordEncoder().encode(userCredentialsForm.getPassword()));
            user.setRole("USER");
            user.setNonExpired(Boolean.TRUE);
            user.setNonLocked(Boolean.TRUE);
            user.setCredentialsNonExpired(Boolean.TRUE);
            user.setEnabled(Boolean.TRUE);

            userRepository.save(user.toEntity());
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataIntegrityViolationException exception) {
            return new ResponseEntity<>(exception.getCause().getCause().getMessage(), HttpStatus.CONFLICT);
        }
    }

    @PostMapping(value = "/login")
    public ResponseEntity<?> loginUser(@RequestBody UserCredentialsForm userCredentialsForm) {
        HashMap<String, String> hashMap = new HashMap<>();
        Optional<User> userByEmail = userRepository.findUserByEmail(userCredentialsForm.getEmail());
        if (userByEmail.isPresent()) {
            if (webSecurityConfig.passwordEncoder().matches(userCredentialsForm.getPassword(), userByEmail.get().getPassword())) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                hashMap.put("password", "Podane hasła nie są zgodne");
                return new ResponseEntity<>(hashMap, HttpStatus.NOT_FOUND);
            }
        } else {
            hashMap.put("email", "Nie ma użytkownika o danym e-mailu");
            return new ResponseEntity<>(hashMap, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/delete-account")
    public ResponseEntity<?> loginUser() {
        User user = UserUtils.getCurrentUser();
        userRepository.delete(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/update-credentials")
    public ResponseEntity<?> updatePassword(@RequestBody(required = false) UserCredentialsForm userCredentialsForm) {
        User user = UserUtils.getCurrentUser();
        try {
            UserDTO userDTO = new UserDTO(user);
            if (userCredentialsForm.getPassword() != null) {
                userDTO.setPassword(webSecurityConfig.passwordEncoder().encode(userCredentialsForm.getPassword()));
            }
            if (userCredentialsForm.getEmail() != null) {
                userDTO.setEmail(userCredentialsForm.getEmail());
            }
            userRepository.save(userDTO.toEntity());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
