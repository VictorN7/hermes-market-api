package com.hermes.market.infrastructure.repository;

import com.hermes.market.domain.user.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
