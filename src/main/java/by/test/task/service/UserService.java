package by.test.task.service;

import by.test.task.dto.user.RegistrationRequest;
import by.test.task.mapper.RegistrationMapper;
import by.test.task.orm.UserEntity;
import by.test.task.orm.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static by.test.task.core.exceptions.AppException.ALREADY_EXIST;

/**
 * Service class for user-related operations.
 */
@RequiredArgsConstructor
@Service
@Slf4j
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final RegistrationMapper mapper;

    /**
     * Registers a new user based on the provided registration request.
     * Throws an exception if a user with the same email already exists.
     *
     * @param req the registration request containing user details
     * @return the newly registered user entity
     * @throws by.test.task.core.exceptions.AppException if a user with the same email already exists
     */
    public UserEntity registerUser(RegistrationRequest req) {
        ALREADY_EXIST.throwIf(userRepository.existsByEmail(req.getEmail()), "User");
        var user = mapper.toEntity(req);
        return userRepository.save(user);
    }
}
