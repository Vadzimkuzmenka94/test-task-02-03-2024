package by.test.task.mapper;

import by.test.task.dto.user.RegistrationRequest;
import by.test.task.dto.user.RegistrationResponse;
import by.test.task.orm.UserEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.function.Function;
/**
 * Mapper class for converting between RegistrationRequest, UserEntity, and RegistrationResponse objects.
 */
@RequiredArgsConstructor
@Component
@Slf4j
public class RegistrationMapper implements Function<UserEntity, RegistrationResponse> {
    private final PasswordEncoder passwordEncoder;

    public UserEntity toEntity(RegistrationRequest req) {
        UserEntity e = new UserEntity();
        e.setLogin(req.getLogin());
        e.setEmail(req.getEmail());
        e.setPassword(passwordEncoder.encode(req.getPassword()));
        return e;
    }

    private RegistrationResponse toResponse(UserEntity user) {
        return RegistrationResponse.builder()
                .email(user.getEmail())
                .id(user.getId())
                .login(user.getLogin())
                .createdDate(user.getCreatedDate())
                .build();
    }

    @Override
    public RegistrationResponse apply(UserEntity user) {
        return toResponse(user);
    }
}
