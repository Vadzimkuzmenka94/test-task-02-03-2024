package by.test.task.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.OffsetDateTime;
import java.util.UUID;

@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Schema(description = "Registration response")
public class RegistrationResponse {
    @Schema(description = "Id",
            example = "b29b4d99-b6eb-4e02-b3d3-fad20d2c1d72")
    private UUID id;
    @Schema(description = "Created date",
            example = "2024-03-02 20:30:12.197977 +00:00")
    private OffsetDateTime createdDate;
    @Schema(description = "Login of the user",
            example = "John")
    private String login;
    @Schema(description = "The email address of the user", example = "john@example.com")
    private String email;
}
