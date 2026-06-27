package com.hermes.market.application.dto.response;

public class LoginResponse {

    private String token;
    private UserResponse user;

    private LoginResponse(){
    }

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
