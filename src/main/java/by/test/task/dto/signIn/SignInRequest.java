package by.test.task.dto.signIn;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Schema(description = "Sing in request ")
public class SignInRequest {
    @NotBlank(message = "Email is required")
    @Email(regexp = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$", message = "Invalid email format")
    @Schema(description = "The email address of the user", example = "john@example.com")
    private String email;

    @NotBlank(message = "Password is required")
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{7,}$",
            message = "Password must contain at least 7 characters, including at least one uppercase letter, one lowercase letter, and one digit")
    @Schema(description = "The password of the user. Must contain at least 7 characters, including at least one uppercase letter, one lowercase letter, and one digit",
            example = "Passw0rd")
    private String password;
}
