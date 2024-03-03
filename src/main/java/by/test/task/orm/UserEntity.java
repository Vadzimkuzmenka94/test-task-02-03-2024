package by.test.task.orm;

import by.test.task.core.orm.UuidEntity;
import by.test.task.security.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

import static java.time.OffsetDateTime.now;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users", schema = "test-task")
public class UserEntity extends UuidEntity {
    @NotNull
    @Column(nullable = false, unique = true)
    private String login;
    @NotNull
    @Column(nullable = false)
    private String password;
    @Column(unique = true)
    private String email;
    @Column
    @Enumerated(EnumType.STRING)
    private UserRole userRole = UserRole.USER;
    @Column(name = "deleted_date")
    private OffsetDateTime deletedDate;

    public void delete() {
        deletedDate = now();
    }
}
