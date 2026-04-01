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

    public List<AddressResponse> findAddressByUser(Long id){
        return addressRepository.findByUserId(id).stream().map(AddressMapper::toResponse).toList();
    }

    public AddressResponse insertAddress(Long userId, AddressRequest addressRequest){
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Address address = AddressMapper.toCreate(addressRequest, user);
        user.addAddress(address);
        userRepository.save(user);
        return AddressMapper.toResponse(address);
    }

    public void deleteAddress(Long userId, Long addressId){

        if (!userRepository.existsById(userId)){
            throw new ResourceNotFoundException("User not found");
        }

        Address address = addressRepository.findById(addressId).orElseThrow(() -> new ResourceNotFoundException("Address not found"));

        if (orderRepository.existsByAddressId(addressId)){
            throw new BusinessException("Address cannot be deleted because it is associated with existing orders");
        }

        addressRepository.delete(address);
    }

}