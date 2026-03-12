package com.hermes.market.application.mapper;

import com.hermes.market.application.dto.response.AddressResponse;
import com.hermes.market.domain.user.Address;

public class AddressMapper {

    private AddressMapper(){
    }

    public static AddressResponse toResponse(Address address){
        return new AddressResponse(address.getId(), address.getNumber(), address.getStreet(), address.getComplement(),
                address.getNeighborhood(), address.getCity(), address.getState(), address.getZipcode());
    }
}
