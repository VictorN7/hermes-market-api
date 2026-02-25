package com.hermes.market.application.dto.response;

import com.hermes.market.domain.user.Role;
import com.hermes.market.domain.user.User;
import com.hermes.market.domain.user.UserStatus;

public class UserResponseDTO {

    private Long id;
    private String name;
    private String email;
    private UserStatus status;
    private Role role;

    public UserResponseDTO(){
    }

    public UserResponseDTO(Long id, String name, String email, UserStatus status, Role role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.status = status;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserResponseDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", status=" + status +
                ", role=" + role +
                '}';
    }
}