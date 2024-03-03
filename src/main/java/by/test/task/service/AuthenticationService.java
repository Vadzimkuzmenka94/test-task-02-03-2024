package by.test.task.service;

import by.test.task.dto.signIn.SignInRequest;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Service;

/**
 * Service class for authentication operations.
 */
@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;

    /**
     * Authenticates a user based on the provided sign-in request and HTTP servlet request.
     *
     * @param signInRequest the sign-in request containing user credentials
     * @param request       the HTTP servlet request
     * @return the authentication object for the authenticated user
     */
    public Authentication getNewAuthentication(SignInRequest signInRequest,
                                               HttpServletRequest request) {
        var token = new UsernamePasswordAuthenticationToken(
                signInRequest.getEmail(), signInRequest.getPassword());
        token.setDetails(new WebAuthenticationDetails(request));
        return authenticationManager.authenticate(token);
    }

    /**
     * Creates a new security context and sets the provided authentication object.
     *
     * @param auth the authentication object
     * @return the created security context
     */
    public SecurityContext createNewContext(Authentication auth) {
        var context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(auth);
        SecurityContextHolder.setContext(context);
        return context;
    }
}
