package com.users.algousers.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.users.algousers.dto.AlgoUsersDto;
import com.users.algousers.dto.AuthRequest;
import com.users.algousers.dto.AuthResponse;
import com.users.algousers.entity.AlgoUsers;
import com.users.algousers.repository.AlgoUsersRepository;
import com.users.algousers.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService service;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AlgoUsersRepository usersRepository;
@PostMapping("/register")
public ResponseEntity<AlgoUsersDto> registerUser(@Valid @RequestBody AlgoUsersDto algoUsersDto) {
    AlgoUsersDto registeredUser = service.saveUser(algoUsersDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
}

//    @PostMapping("/token")
//    public String getToken(@RequestBody AuthRequest authRequest) {
//        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
//        if (authenticate.isAuthenticated()) {
//            return service.generateToken(authRequest.getUsername());
//        } else {
//            throw new RuntimeException("invalid access");
//        }
//    }

    @PostMapping("/token")
    public AuthResponse getToken(@RequestBody AuthRequest authRequest) {
        // Authenticate the user
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );

        if (authenticate.isAuthenticated()) {
            // Generate JWT token
            String token = service.generateToken(authRequest.getUsername());

            // Fetch user details (You can fetch the user based on username or email from the database)
            Optional<AlgoUsers > user = usersRepository.findByEmail(authRequest.getUsername()); // Or use email if preferred

            if (user.isPresent()) {
                return new AuthResponse(token, user.get().getFirstName(), user.get().getEmail());
            } else {
                throw new RuntimeException("User not found");
            }
        } else {
            throw new RuntimeException("Invalid access");
        }
    }


    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token) {
        service.validateToken(token);
        return "Token is valid";
    }

}
