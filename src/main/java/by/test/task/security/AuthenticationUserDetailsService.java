package by.test.task.security;

import by.test.task.orm.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static by.test.task.core.exceptions.AppException.NOT_FOUND;
/**
 * Service class for creating custom user details for authentication.
 */
@RequiredArgsConstructor
@Service
@Slf4j
public class AuthenticationUserDetailsService {
    private final UserRepository repository;

    /**
     * Creates custom user details based on the provided email.
     *
     * @param email the email of the user
     * @return the AuthenticationUserDetails object containing user details
     * @throws by.test.task.core.exceptions.AppException if the user with the specified email is not found
     */
    public AuthenticationUserDetails createCustomUserDetailsByEmail(String email) {
        var user = repository.findByEmail(email);
        NOT_FOUND.throwIf(user.isEmpty(), "User");
        return new AuthenticationUserDetails(user.get());
    }
}
