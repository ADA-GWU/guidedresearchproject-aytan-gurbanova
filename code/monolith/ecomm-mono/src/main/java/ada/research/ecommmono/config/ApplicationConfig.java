package ada.research.ecommmono.config;

import ada.research.ecommmono.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.logging.Logger;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {
    private static final Logger logger = Logger.getLogger(ApplicationConfig.class.getName());
    private final UserRepository repository;

    @Bean
    public UserDetailsService userDetailsService() {
        logger.info("userDetailsService method started");
        return username -> repository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with email %s is not found.",
                        username)));
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        logger.info("authenticationProvider method started");
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        logger.info("authenticationProvider bean created");
        logger.info("authenticationProvider method ended");
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        logger.info("authenticationManager method started");
        var manager = config.getAuthenticationManager();
        logger.info("authenticationManager bean created");
        logger.info("authenticationManager method ended");
        return manager;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        logger.info("passwordEncoder method started");
        logger.info("passwordEncoder bean created");
        logger.info("passwordEncoder method ended");
        return new BCryptPasswordEncoder();
    }
}
