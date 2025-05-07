package mk.ukim.finki.emt.rentalapp.web.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import io.swagger.v3.oas.annotations.Operation;
import mk.ukim.finki.emt.rentalapp.dto.CreateUserDto;
import mk.ukim.finki.emt.rentalapp.dto.DisplayUserDto;
import mk.ukim.finki.emt.rentalapp.dto.LoginResponseDto;
import mk.ukim.finki.emt.rentalapp.dto.LoginUserDto;
import mk.ukim.finki.emt.rentalapp.model.exceptions.InvalidArgumentsException;
import mk.ukim.finki.emt.rentalapp.model.exceptions.InvalidUserCredentialsException;
import mk.ukim.finki.emt.rentalapp.model.exceptions.PasswordsDoNotMatchException;
import mk.ukim.finki.emt.rentalapp.service.application.UserApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@Tag(name = "User API", description = "Endpoints for user authentication and registration")
public class UserController {
    private final UserApplicationService userApplicationService;

    public UserController(UserApplicationService userApplicationService) {
        this.userApplicationService = userApplicationService;
    }

    @Operation(summary = "Register a new user", description = "Creates a new user account")
    @PostMapping("/register")
    public ResponseEntity<DisplayUserDto> register(@RequestBody CreateUserDto createUserDto) {
        try {
            return userApplicationService.register(createUserDto)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (InvalidArgumentsException | PasswordsDoNotMatchException exception) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "User login", description = "Authenticates a user and starts a session")
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginUserDto loginUserDto) {
        try {
            return userApplicationService.login(loginUserDto)
                    .map(ResponseEntity::ok)
                    .orElseThrow(InvalidUserCredentialsException::new);
        } catch (InvalidUserCredentialsException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @Operation(summary = "User logout", description = "Ends the user's session")
    @GetMapping("/logout")
    public void logout(HttpServletRequest request) {
        request.getSession().invalidate();
    }
}
