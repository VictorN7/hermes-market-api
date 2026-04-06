package com.hermes.market.application.service;

import com.hermes.market.application.dto.request.AddressRequest;
import com.hermes.market.application.dto.response.AddressResponse;
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
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional(readOnly = true)
    public Page<AddressResponse> findAddressByUser(Long id, Pageable pageable) {
        Page<Address> addresses = addressRepository.findByUserId(id, pageable);
        return addresses.map(AddressMapper::toResponse);
    }

    @Transactional
    public AddressResponse insertAddress(Long userId, AddressRequest addressRequest) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Address address = AddressMapper.toCreate(addressRequest, user);
        addressRepository.saveAndFlush(address);

        return AddressMapper.toResponse(address);
    }

    @Transactional
    public void deleteOrDeactivateAddress(Long userId, Long addressId) {

        Address address = addressRepository.findById(addressId).orElseThrow(() -> new ResourceNotFoundException("Address not found"));
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (!address.getUser().getId().equals(user.getId())) {
            throw new ResourceNotFoundException("Address does not belong to this user");
        }

        if (orderRepository.existsByAddressId(addressId)) {
            address.deactivate();
            addressRepository.save(address);
        } else {
            addressRepository.delete(address);
        }
    }

}