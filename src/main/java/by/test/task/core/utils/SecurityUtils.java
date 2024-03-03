package by.test.task.core.utils;

import by.test.task.security.AuthenticationUserDetails;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Utility class for accessing security-related information.
 */
@UtilityClass
public class SecurityUtils {
    /**
     * Retrieves the UserDetails of the currently authenticated user.
     *
     * @return the UserDetails of the currently authenticated user
     * @throws ClassCastException if the principal is not of type AuthenticationUserDetails
     */
    public static AuthenticationUserDetails getPrincipal() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var details = (AuthenticationUserDetails) authentication.getPrincipal();
        return details;
    }
}