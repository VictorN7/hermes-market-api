package com.hermes.market.application.mapper;

import com.hermes.market.application.dto.request.LoginRequest;
import com.hermes.market.application.dto.request.UserRequest;
import com.hermes.market.application.dto.response.LoginResponse;
import com.hermes.market.application.dto.response.UserResponse;
import com.hermes.market.domain.user.User;

public class UserMapper {

    private UserMapper(){
    }

    public static UserResponse toResponse(User user){
        return new UserResponse(user.getId(), user.getName(), user.getEmail(),
                user.getStatus().name(), user.getRole().name());
    }

    public static User toCreate(UserRequest userRequest){
        return new User(userRequest.getName(), userRequest.getEmail(), userRequest.getPassword(), userRequest.getBirthDate(), userRequest.getCpf());
    }

    public static LoginResponse toLogin(User user){
        return new LoginResponse(user.getId(), user.getName(), user.getEmail(), user.getRole().name());
    }

}
