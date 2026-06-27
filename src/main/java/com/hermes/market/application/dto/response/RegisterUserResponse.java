package com.hermes.market.application.dto.response;

public class RegisterUserResponse {

    private final String name;
    private final String email;
    
    public RegisterUserResponse(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "RegisterUserResponse{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
