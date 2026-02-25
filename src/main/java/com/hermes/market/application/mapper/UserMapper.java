package com.hermes.market.application.mapper;

import com.hermes.market.application.dto.response.UserResponseDTO;
import com.hermes.market.domain.user.User;

public class UserMapper {

    private UserMapper(){
    }

    public static UserResponseDTO toResponse(User user){
        return new UserResponseDTO(user.getId(), user.getName(), user.getEmail(), user.getStatus(), user.getRole());
    }
}
