package com.hermes.market.web.controller.client;

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
import jakarta.validation.constraints.Positive;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.hermes.market.application.service.UserService;

@RestController
@RequestMapping("/api/v1/users")
@Validated
public class UserController {

	private final UserService userService;
	private final OrderService orderService;
	private final AddressService addressService;

	public UserController(UserService userService, OrderService orderService, AddressService addressService) {
		this.userService = userService;
		this.orderService = orderService;
		this.addressService = addressService;
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserResponse> findById(@PathVariable @Positive Long id){
		return ResponseEntity.ok().body(userService.findById(id));
	}

	@GetMapping("/{id}/orders")
	public ResponseEntity<Page<OrderSummaryResponse>> findOrdersByUser(@PathVariable @Positive Long id, Pageable pageable){
		return ResponseEntity.ok().body(orderService.findOrdersByUser(id, pageable));
	}

	@GetMapping("/{id}/addresses/{addressId}")
	public ResponseEntity<AddressResponse> findAddressById(@PathVariable @Positive Long id, @PathVariable @Positive Long addressId){
		return ResponseEntity.ok().body(addressService.findAddressById(id, addressId));
	}

	@GetMapping("/{id}/addresses")
	public ResponseEntity<Page<AddressResponse>> findAllAddress(@PathVariable @Positive Long id, Pageable pageable){
		return ResponseEntity.ok().body(addressService.findAddressByUser(id, pageable));
	}

	@PostMapping
	public ResponseEntity<UserResponse> createUser(@RequestBody @Valid UserRequest userRequest){
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userRequest));
	}

	@PostMapping("/{id}/addresses")
	public ResponseEntity<AddressResponse> insertAddress(@PathVariable @Positive Long id, @RequestBody @Valid AddressRequest addressRequest){
		return ResponseEntity.status(HttpStatus.CREATED).body(addressService.insertAddress(id, addressRequest));
	}

	@PutMapping("/{id}")
	public ResponseEntity<UserResponse> updateUser(@PathVariable @Positive Long id, @RequestBody @Valid UserUpdateRequest userUpdateRequest){
		return ResponseEntity.ok().body(userService.updateUser(id, userUpdateRequest));
	}

	@PutMapping("/{id}/addresses/{addressId}")
	public ResponseEntity<AddressResponse> updateAddress(@PathVariable @Positive Long id, @PathVariable @Positive Long addressId, @RequestBody @Valid AddressRequest addressRequest){
		return ResponseEntity.ok().body(addressService.updateAddress(id, addressId, addressRequest));
	}

	@PatchMapping("/{id}/password")
	public ResponseEntity<Void> updatePassword(@PathVariable @Positive Long id, @RequestBody @Valid UserPasswordRequest userPasswordRequest){

		userService.updatePassword(id, userPasswordRequest);
		return ResponseEntity.noContent().build();
	}

	@PatchMapping("/{id}/deactivate")
	public ResponseEntity<Void> deactivateUser(@PathVariable @Positive Long id){

		userService.deactivateUser(id);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteOrDeactivateUser(@PathVariable @Positive Long id){

		userService.deleteOrDeactivateUser(id);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{id}/addresses/{addressId}")
	public ResponseEntity<Void> deleteOrDeactivateAddress(@PathVariable @Positive Long id, @PathVariable @Positive Long addressId){

		addressService.deleteOrDeactivateAddress(id, addressId);
		return ResponseEntity.noContent().build();
	}
}
