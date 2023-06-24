package ada.research.ecommmono.service;

import ada.research.ecommmono.model.User;
import ada.research.ecommmono.model.UserUpdateResponse;
import ada.research.ecommmono.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository repository;
    private final static String USER_NOT_FOUND_MESSAGE = "User with email %s not found.";

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        logger.info("UserService - loadUserByUsername started for email: {}", email);
        UserDetails userDetails = repository.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MESSAGE, email)));
        logger.info("UserService - loadUserByUsername ended for email: {}", email);
        return userDetails;
    }

    public UserUpdateResponse updateUser(String email, String address, String phoneNumber) {
        logger.info("UserService - updateUser started for email: {}", email);
        User user = repository.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MESSAGE, email)));
        if (user != null) {
            user.setAddress(address);
            user.setPhoneNumber(phoneNumber);
            user.setUpdatedAt(new Date());
            User updatedUser = repository.save(user);
            UserUpdateResponse userUpdateResponse = new UserUpdateResponse(
                    email, updatedUser.getFirstName(), updatedUser.getLastName(),
                    updatedUser.getAddress(), updatedUser.getPhoneNumber(),
                    updatedUser.getCreatedAt(), updatedUser.getUpdatedAt()
            );
            logger.info("UserService - updateUser ended for email: {}", email);
            return userUpdateResponse;
        }
        logger.info("UserService - updateUser ended for email: {}. User not found.", email);
        return null;
    }
}
