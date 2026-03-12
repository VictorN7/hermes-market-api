package com.hermes.market.application.service;

import com.hermes.market.application.dto.response.AddressResponse;
import com.hermes.market.application.mapper.AddressMapper;
import com.hermes.market.infrastructure.repository.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public List<AddressResponse> findAddressByUser(Long id){
        return addressRepository.findByUserId(id).stream().map(AddressMapper::toResponse).toList();
    }
}