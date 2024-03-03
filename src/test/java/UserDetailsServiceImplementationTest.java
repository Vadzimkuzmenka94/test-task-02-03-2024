import by.test.task.orm.UserEntity;
import by.test.task.security.AuthenticationUserDetails;
import by.test.task.security.AuthenticationUserDetailsService;
import by.test.task.security.UserDetailsServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static constants.TestConstants.EMAIL;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserDetailsServiceImplementationTest {

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Mock
    private AuthenticationUserDetailsService customUserDetailsService;


    @Test
    void shouldLoadUserByEmail() {
        var testUser = new UserEntity();
        var expectedUserDetails = new AuthenticationUserDetails(testUser);
        when(customUserDetailsService.createCustomUserDetailsByEmail(EMAIL)).thenReturn(
                expectedUserDetails);

        var result = userDetailsService.loadUserByUsername(EMAIL);
        verify(customUserDetailsService, times(1)).createCustomUserDetailsByEmail(EMAIL);

        assertEquals(expectedUserDetails, result);
    }
}