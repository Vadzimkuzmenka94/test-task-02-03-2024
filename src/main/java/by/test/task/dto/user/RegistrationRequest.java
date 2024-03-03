package by.test.task.dto.user;

import by.test.task.security.UserRole;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Schema(description = "Registration request")
public class RegistrationRequest {
    @NotBlank(message = """
            Login is required""")
    @Size(min = 3, max = 30, message = """
            Login must contain from 3 to 30 characters""")
    @Schema(description = """
            The username of the user. Username must contain from 3 to 30 characters.""", example = "John")
    private String login;

    @NotBlank(message = """
            Password is required""")
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{7,}$", message = """
            Password must contain at least 7 characters, including at least one uppercase letter,
            one lowercase letter, and one digit""")
    @Schema(description = """
            The password of the user. The password must contain at least 7 characters, 
            at least 1 capital letter of the Latin language, different case""", example = "Passw0rd")
    private String password;

    @NotBlank(message = """
            Email is required""")
    @Email(regexp = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$", message = """
            Invalid email format""")
    @Schema(description = """
            The email address of the user""", example = "john@example.com")
    private String email;

    @Schema(description = """
            The role of the user""", example = "USER")
    private UserRole userRole = UserRole.USER;
}
