package ada.research.ecommmono.controller;

import ada.research.ecommmono.model.AuthenticationRequest;
import ada.research.ecommmono.model.AuthenticationResponse;
import ada.research.ecommmono.model.RegisterRequest;
import ada.research.ecommmono.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mono/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return service.register(request);
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return service.authenticate(request);
    }
}
