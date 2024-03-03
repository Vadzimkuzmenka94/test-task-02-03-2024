package by.test.task.web;

import by.test.task.dto.signIn.SignInRequest;
import by.test.task.dto.signIn.SingInResponse;
import by.test.task.security.AuthenticationUserDetails;
import by.test.task.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static by.test.task.core.Constants.WELCOME;

@RestController
@RequiredArgsConstructor
@RequestMapping("/signin")
@Tag(name = "Authentication", description = "Class for authentication operations")
public class AuthenticationController {
    private final AuthenticationService service;
    private final HttpSessionSecurityContextRepository securityContextRepository =
            new HttpSessionSecurityContextRepository();

    @Operation(summary = "Sign in with provided credentials")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User signed in successfully"),
            @ApiResponse(responseCode = "403", description = "Invalid sign in request.Forbidden")
    })
    @PostMapping
    public SingInResponse signIn(
            @Valid @RequestBody SignInRequest signInRequest,
            HttpServletRequest request,
            HttpServletResponse response) {
        Authentication authentication;
        authentication = service.getNewAuthentication(signInRequest, request);
        var context = service.createNewContext(authentication);
        securityContextRepository.saveContext(context, request, response);
        var userDetails = (AuthenticationUserDetails) authentication.getPrincipal();
        return SingInResponse.builder().welcomeText(WELCOME + userDetails.getUsername()).build();
    }
}
