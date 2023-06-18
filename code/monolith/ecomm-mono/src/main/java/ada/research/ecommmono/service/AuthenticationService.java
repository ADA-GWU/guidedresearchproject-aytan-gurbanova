package ada.research.ecommmono.service;

import ada.research.ecommmono.model.*;
import ada.research.ecommmono.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private static final Logger logger = Logger.getLogger(AuthenticationService.class.getName());

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        logger.info(String.format("register method started with request: %s", request.toString()));
        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .userRole(UserRole.USER)
                .enabled(true)
                .locked(false)
                .createdAt(new Date())
                .updatedAt(new Date())
                .build();
        userRepository.save(user);
        logger.info(String.format("User registered: %s", user));

        var token = jwtService.generateToken(user);
        logger.info("Token generated");
        logger.info("register method ended");
        return AuthenticationResponse.builder().token(token).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request){
        logger.info("authenticate method started");
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
        ));
        logger.info("User authenticated");

        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        logger.info(String.format("User retrieved from database: %s", user));

        var token = jwtService.generateToken(user);
        logger.info("Token generated");
        logger.info("authenticate method ended");
        return AuthenticationResponse.builder().token(token).build();
    }
}
