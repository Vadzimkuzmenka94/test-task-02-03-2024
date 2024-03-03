package by.test.task.dto.signIn;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Schema(description = "Sing in response ")
public class SingInResponse {
    @Schema(description = "Welcome text for current user", example = "Welcome Toni!")
    private String welcomeText;
}
