package ada.research.ecommmono.controller;

import ada.research.ecommmono.model.RegistrationRequest;
import ada.research.ecommmono.service.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "ecomm/registration")
@AllArgsConstructor
public class RegistrationController {
    RegistrationService registrationService;
    @PostMapping
    public String register(@RequestBody RegistrationRequest request){
        return registrationService.register(request);
    }
}
