import by.test.task.dto.signIn.SignInRequest;
import by.test.task.orm.UserEntity;
import by.test.task.orm.UserRepository;
import by.test.task.security.AuthenticationUserDetails;
import by.test.task.security.AuthenticationUserDetailsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static constants.TestConstants.EMAIL;
import static constants.TestConstants.PASSWORD;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthenticationUserDetailsServiceTest {
    @InjectMocks
    private AuthenticationUserDetailsService customUserDetailsService;

    @Mock
    private UserRepository repository;


    @Test
    void shouldCreateCustomUserDetails() {
        SignInRequest req = new SignInRequest();
        req.setEmail(EMAIL);
        req.setPassword(PASSWORD);
        UserEntity user = new UserEntity();
        user.setEmail(req.getEmail());
        user.setPassword(req.getPassword());

        when(repository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
        AuthenticationUserDetails result =
                customUserDetailsService.createCustomUserDetailsByEmail(user.getEmail());

        assertNotNull(result);
        assertEquals(req.getEmail(), result.getEmail());
    }
}
