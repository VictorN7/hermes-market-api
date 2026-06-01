package com.hermes.market.web.controller.admin;

import com.hermes.market.application.dto.response.UserResponse;
import com.hermes.market.application.service.UserService;
import jakarta.validation.constraints.Positive;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/users")
@Validated
public class AdminUserController {

    private final UserService userService;

    public AdminUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<Page<UserResponse>> findAll(Pageable pageable) {
        return ResponseEntity.ok().body(userService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable @Positive Long id){
        return ResponseEntity.ok(userService.findById(id));
    }

    @GetMapping("/inactive")
    public ResponseEntity<Page<UserResponse>> findInactiveUsers(Pageable pageable){
        return ResponseEntity.ok().body(userService.findInactiveUsers(pageable));
    }

    @GetMapping("/blocked")
    public ResponseEntity<Page<UserResponse>> findBlockedUsers(Pageable pageable){
        return ResponseEntity.ok().body(userService.findBlockedUsers(pageable));
    }

    @GetMapping("/inactive/{id}")
    public ResponseEntity<UserResponse> findInactiveUserById(@PathVariable @Positive Long id){
        return ResponseEntity.ok().body(userService.findInactiveUserById(id));
    }

    @PatchMapping("/{id}/activate")
    public ResponseEntity<Void> activateUser(@PathVariable @Positive Long id){

        userService.activateUser(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/block")
    public ResponseEntity<Void> blockUser(@PathVariable @Positive Long id){

        userService.blockUser(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/unlock")
    public ResponseEntity<Void> unlockUser(@PathVariable @Positive Long id){

        userService.unlockUser(id);
        return ResponseEntity.noContent().build();
    }

}
