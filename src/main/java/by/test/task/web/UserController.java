package by.test.task.web;

import by.test.task.dto.user.RegistrationRequest;
import by.test.task.dto.user.RegistrationResponse;
import by.test.task.mapper.RegistrationMapper;
import by.test.task.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Validated
@RestController
@Tag(name = "Users", description = "Class for user registration")
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final RegistrationMapper mapper;

    @Operation(summary = "Register a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User registered successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid user registration request")
    })
    @PostMapping
    public RegistrationResponse register(@Valid @RequestBody RegistrationRequest req) {
        var user = userService.registerUser(req);
        return mapper.apply(user);
    }
}
