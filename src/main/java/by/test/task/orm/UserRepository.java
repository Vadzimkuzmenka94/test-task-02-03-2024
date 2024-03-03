package by.test.task.orm;

import by.test.task.core.orm.UuidRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
/**
 * Repository interface for managing UserEntity entities.
 */
@Repository
public interface UserRepository extends UuidRepository<UserEntity> {
    /**
     * Checks if a user with the specified email exists.
     *
     * @param email the email to check
     * @return true if a user with the specified email exists, false otherwise
     */
    boolean existsByEmail(String email);
    /**
     * Retrieves a user by email.
     *
     * @param email the email of the user to retrieve
     * @return an Optional containing the user with the specified email, or empty if not found
     */
    Optional<UserEntity> findByEmail(String email);

}
