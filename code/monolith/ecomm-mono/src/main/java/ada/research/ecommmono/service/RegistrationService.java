package ada.research.ecommmono.service;

import ada.research.ecommmono.model.RegistrationRequest;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {
    public String register(RegistrationRequest request){
        return "success";
    }
}
