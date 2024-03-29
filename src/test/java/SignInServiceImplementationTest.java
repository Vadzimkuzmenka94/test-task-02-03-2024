import by.test.task.dto.signIn.SignInRequest;
import by.test.task.service.AuthenticationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import static constants.TestConstants.EMAIL;
import static constants.TestConstants.PASSWORD;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class SignInServiceImplementationTest {
    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private AuthenticationService signInService;

    @Test
    void shouldReturnAuthentication_whenGetNewAuthenticationWithValidCredentials() {
        var httpSession = new MockHttpSession();
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setSession(httpSession);
        var signInRequest = new SignInRequest();
        signInRequest.setEmail(EMAIL);
        signInRequest.setPassword(PASSWORD);
        var authenticationDetails = new WebAuthenticationDetails(request);

        ArgumentCaptor<UsernamePasswordAuthenticationToken> captor =
                ArgumentCaptor.forClass(UsernamePasswordAuthenticationToken.class);

        signInService.getNewAuthentication(signInRequest, request);

        verify(authenticationManager).authenticate(captor.capture());
        UsernamePasswordAuthenticationToken capturedToken = captor.getValue();
        assertEquals(authenticationDetails, capturedToken.getDetails());
        assertEquals(EMAIL, capturedToken.getPrincipal());
        assertEquals(PASSWORD, capturedToken.getCredentials());
    }

    @Test
    void shouldReturnSecurityContext_whenCreateNewContextWithAuthentication() {
        var authentication =
                new UsernamePasswordAuthenticationToken(EMAIL, PASSWORD);
        var newContext = signInService.createNewContext(authentication);
        assertEquals(authentication, newContext.getAuthentication());
    }
}