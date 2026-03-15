package com.hermes.market.application.mapper;

import com.hermes.market.application.dto.request.AddressRequest;
import com.hermes.market.application.dto.response.AddressResponse;
import com.hermes.market.domain.user.Address;
import com.hermes.market.domain.user.User;

public class AddressMapper {

    private AddressMapper(){
    }

    public static AddressResponse toResponse(Address address){
        return new AddressResponse(address.getId(), address.getNumber(), address.getStreet(), address.getComplement(),
                address.getNeighborhood(), address.getCity(), address.getState(), address.getZipcode());
    }

    public static Address toCreate(AddressRequest addressRequest, User user){
        return new Address(addressRequest.getStreet(), addressRequest.getNumber(),addressRequest.getComplement(), addressRequest.getNeighborhood(),
                addressRequest.getCity(), addressRequest.getState(), addressRequest.getZipcode(), user);
    }
}
