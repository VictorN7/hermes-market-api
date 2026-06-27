package com.hermes.market.web.controller.auth;

import com.hermes.market.application.dto.request.LoginRequest;
import com.hermes.market.application.dto.request.UserRequest;
import com.hermes.market.application.dto.response.LoginResponse;
import com.hermes.market.application.dto.response.UserResponse;
import com.hermes.market.application.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest loginRequest){
        return ResponseEntity.ok().body(userService.toLogin(loginRequest));
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(@RequestBody @Valid UserRequest userRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userRequest));
    }

}
