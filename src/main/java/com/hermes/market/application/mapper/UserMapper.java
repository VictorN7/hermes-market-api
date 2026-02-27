package com.hermes.market.application.mapper;

import com.hermes.market.application.dto.response.UserResponse;
import com.hermes.market.domain.user.User;

public class UserMapper {

    private UserMapper(){
    }

    public static UserResponse toResponse(User user){
        return new UserResponse(user.getId(), user.getName(), user.getEmail(), user.getStatus().toString(), user.getRole().toString());
    }
}
