package com.hermes.market.application.service;

import com.hermes.market.application.dto.request.AddressRequest;
import com.hermes.market.application.dto.response.AddressResponse;
import com.hermes.market.application.exception.ResourceNotFoundException;
import com.hermes.market.application.mapper.AddressMapper;
import com.hermes.market.domain.user.Address;
import com.hermes.market.domain.user.User;
import com.hermes.market.infrastructure.repository.AddressRepository;
import com.hermes.market.infrastructure.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    private final AddressRepository addressRepository;
    private final UserRepository userRepository;

    public AddressService(AddressRepository addressRepository, UserRepository userRepository) {
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
    }

    public List<AddressResponse> findAddressByUser(Long id){
        return addressRepository.findByUserId(id).stream().map(AddressMapper::toResponse).toList();
    }

    public AddressResponse insertAddress(Long userId, AddressRequest addressRequest){
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found!"));
        Address address = AddressMapper.toCreate(addressRequest, user);
        user.addAddress(address);
        userRepository.save(user);
        return AddressMapper.toResponse(address);
    }
}