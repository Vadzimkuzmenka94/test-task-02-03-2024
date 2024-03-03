import by.test.task.core.exceptions.BaseException;
import by.test.task.dto.user.RegistrationRequest;
import by.test.task.mapper.RegistrationMapper;
import by.test.task.orm.UserEntity;
import by.test.task.orm.UserRepository;
import by.test.task.security.UserRole;
import by.test.task.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static constants.TestConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RegistrationMapper mapper;

    @InjectMocks
    private UserService userService;

    @Test
    void registerUser_SuccessfulRegistration() {
        RegistrationRequest request = new RegistrationRequest(LOGIN,PASSWORD , EMAIL, UserRole.USER);
        UserEntity newUser = new UserEntity(LOGIN, PASSWORD, EMAIL,  UserRole.USER, null);

        Mockito.when(userRepository.existsByEmail(Mockito.anyString())).thenReturn(false);
        Mockito.when(mapper.toEntity(request)).thenReturn(newUser);
        Mockito.when(userRepository.save(newUser)).thenReturn(newUser);

        UserEntity registeredUser = userService.registerUser(request);

        Assertions.assertEquals(newUser.getEmail(), registeredUser.getEmail());
        Mockito.verify(userRepository, Mockito.times(1)).existsByEmail(EMAIL);
        Mockito.verify(mapper, Mockito.times(1)).toEntity(request);
        Mockito.verify(userRepository, Mockito.times(1)).save(newUser);
    }


    @Test
    void registerUser_UserWithEmailAlreadyExists_ThrowsException() {
        RegistrationRequest request = new RegistrationRequest(LOGIN, PASSWORD, EMAIL, UserRole.USER);

        Mockito.when(userRepository.existsByEmail(Mockito.anyString())).thenReturn(true);

        BaseException exception = assertThrows(BaseException.class, () -> userService.registerUser(request));
        assertEquals("Entity User already exists", exception.getMessage());

        Mockito.verify(userRepository, Mockito.times(1)).existsByEmail(EMAIL);
    }

}
