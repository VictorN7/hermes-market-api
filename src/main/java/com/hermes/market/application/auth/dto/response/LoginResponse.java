package com.hermes.market.application.auth.dto.response;

import com.hermes.market.application.dto.response.UserResponse;

public class LoginResponse {

    private final String token;
    private final UserResponse user;

    public LoginResponse(String token, UserResponse user) {
        this.token = token;
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public UserResponse getUser() {
        return user;
    }
}
