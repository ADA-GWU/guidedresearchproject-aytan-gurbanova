package ada.research.ecommmono.controller;

import ada.research.ecommmono.model.UserUpdateRequest;
import ada.research.ecommmono.model.User;
import ada.research.ecommmono.model.UserUpdateResponse;
import ada.research.ecommmono.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/mono/v1/user")
public class UserController {
    private final UserService userService;

    @PutMapping("/update")
    public ResponseEntity<UserUpdateResponse> updateUserInformation(
            @RequestBody UserUpdateRequest userUpdateRequest
            ) {
        UserUpdateResponse updatedUser = userService.updateUser(
                userUpdateRequest.getEmail(),
                userUpdateRequest.getAddress(),
                userUpdateRequest.getPhoneNumber());
        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
