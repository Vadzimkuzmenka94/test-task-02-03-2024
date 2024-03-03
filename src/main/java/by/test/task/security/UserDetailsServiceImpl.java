package by.test.task.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
/**
 * Service class for loading user details by username (email).
 */
@RequiredArgsConstructor
@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {
    private final AuthenticationUserDetailsService authenticationUserDetailsService;

    /**
     * Loads user details by the given username (email).
     *
     * @param email the username (email) of the user
     * @return the UserDetails object containing user details
     * @throws UsernameNotFoundException if the user with the specified email is not found
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return authenticationUserDetailsService.createCustomUserDetailsByEmail(email);
    }
}
