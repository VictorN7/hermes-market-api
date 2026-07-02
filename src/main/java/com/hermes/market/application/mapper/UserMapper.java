package com.hermes.market.application.mapper;


import com.hermes.market.application.auth.dto.response.LoginResponse;
import com.hermes.market.application.dto.response.UserResponse;
import com.hermes.market.domain.user.User;

public class UserMapper {

    private UserMapper(){
    }

    public static UserResponse toResponse(User user){
        return new UserResponse(user.getId(), user.getName(), user.getEmail(),
                user.getStatus().name(), user.getRole().name());
    }

    public static LoginResponse toLogin(String token, User user){
        return new LoginResponse(token, UserMapper.toResponse(user));
    }

}
