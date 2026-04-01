package com.hermes.market.web.controller;

import java.util.List;

import com.hermes.market.application.dto.request.AddressRequest;
import com.hermes.market.application.dto.request.UserPasswordRequest;
import com.hermes.market.application.dto.request.UserRequest;
import com.hermes.market.application.dto.request.UserUpdateRequest;
import com.hermes.market.application.dto.response.AddressResponse;
import com.hermes.market.application.dto.response.OrderSummaryResponse;
import com.hermes.market.application.dto.response.UserResponse;
import com.hermes.market.application.service.AddressService;
import com.hermes.market.application.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.hermes.market.application.service.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

	private final UserService userService;
	private final OrderService orderService;
	private final AddressService addressService;
	
	public UserController(UserService userService, OrderService orderService, AddressService addressService) {
		this.userService = userService;
		this.orderService = orderService;
		this.addressService = addressService;
	}

	@GetMapping
	public ResponseEntity<List<UserResponse>> findAll(){
		return ResponseEntity.ok().body(userService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserResponse> findById(@PathVariable Long id){
		return ResponseEntity.ok().body(userService.findById(id));
	}

	@GetMapping("/{id}/orders")
	public ResponseEntity<List<OrderSummaryResponse>> findOrdersByUser(@PathVariable Long id){
		return ResponseEntity.ok().body(orderService.findOrdersByUser(id));
	}

	@GetMapping("/{id}/addresses")
	public ResponseEntity<List<AddressResponse>> findAllAddress(@PathVariable Long id){
		return ResponseEntity.ok().body(addressService.findAddressByUser(id));
	}

	@PostMapping
	public ResponseEntity<UserResponse> createUser(@RequestBody @Valid UserRequest userRequest){
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userRequest));
	}

	@PostMapping("/{id}/addresses")
	public ResponseEntity<AddressResponse> insertAddress(@PathVariable Long id, @RequestBody @Valid AddressRequest addressRequest){
		return ResponseEntity.status(HttpStatus.CREATED).body(addressService.insertAddress(id, addressRequest));
	}

	@PutMapping("/{id}")
	public ResponseEntity<UserResponse> updateUser(@PathVariable Long id, @RequestBody @Valid UserUpdateRequest userUpdateRequest){
		return ResponseEntity.ok().body(userService.updateUser(id, userUpdateRequest));
	}

	@PatchMapping("/{id}/password")
	public ResponseEntity<Void> updatePassword(@PathVariable Long id, @RequestBody @Valid UserPasswordRequest userPasswordRequest){

		userService.updatePassword(id, userPasswordRequest);
		return ResponseEntity.noContent().build();
	}

	@PatchMapping("/{id}/deactivate")
	public ResponseEntity<Void> deactivateUser(@PathVariable Long id){

		userService.deactivateUser(id);
		return ResponseEntity.noContent().build();
	}

	@PatchMapping("/{id}/activate")
	public ResponseEntity<Void> activateUser(@PathVariable Long id){

		userService.activateUser(id);
		return ResponseEntity.noContent().build();
	}

	@PatchMapping("/{id}/block")
	public ResponseEntity<Void> blockUser(@PathVariable Long id){

		userService.blockUser(id);
		return ResponseEntity.noContent().build();
	}

	@PatchMapping("/{id}/unlock")
	public ResponseEntity<Void> unlockUser(@PathVariable Long id){

		userService.unlockUser(id);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteOrDeactivateUser(@PathVariable Long id){

		userService.deleteOrDeactivateUser(id);
		return ResponseEntity.noContent().build();
	}

}
