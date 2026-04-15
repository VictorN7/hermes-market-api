package com.hermes.market.infrastructure.repository;

import com.hermes.market.domain.user.Address;
import com.hermes.market.domain.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {

    Page<Address> findByUserId (Long id, Pageable pageable);

    boolean existsByUserAndStreetIgnoreCaseAndNumber(User user, String street, Integer number);

}
