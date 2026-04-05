package com.hermes.market.application.service;

import com.hermes.market.application.dto.request.AddressRequest;
import com.hermes.market.application.dto.response.AddressResponse;
import com.hermes.market.application.exception.BusinessException;
import com.hermes.market.application.exception.ResourceNotFoundException;
import com.hermes.market.application.mapper.AddressMapper;
import com.hermes.market.domain.user.Address;
import com.hermes.market.domain.user.User;
import com.hermes.market.infrastructure.repository.AddressRepository;
import com.hermes.market.infrastructure.repository.OrderRepository;
import com.hermes.market.infrastructure.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    private final AddressRepository addressRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    public AddressService(AddressRepository addressRepository, UserRepository userRepository, OrderRepository orderRepository) {
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
    }

    public Page<AddressResponse> findAddressByUser(Long id, Pageable pageable) {
        Page<Address> addresses = addressRepository.findByUserId(id, pageable);
        return addresses.map(AddressMapper::toResponse);
    }

    public AddressResponse insertAddress(Long userId, AddressRequest addressRequest) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Address address = AddressMapper.toCreate(addressRequest, user);
        user.addAddress(address);
        userRepository.save(user);
        return AddressMapper.toResponse(address);
    }

    public void deleteOrDeactivateAddress(Long userId, Long addressId) {

        Address address = addressRepository.findById(addressId).orElseThrow(() -> new ResourceNotFoundException("Address not found"));

        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("User not found");
        }

        if (orderRepository.existsByAddressId(addressId)) {
            address.deactivate();
            addressRepository.save(address);
        } else {
            addressRepository.delete(address);
        }
    }

}