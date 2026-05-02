package com.hermes.market.infrastructure.repository;

import com.hermes.market.domain.user.Address;
import com.hermes.market.domain.user.AddressStatus;
import com.hermes.market.domain.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {

    boolean existsByUserAndStreetIgnoreCaseAndNumber(User user, String street, Integer number);

    Page<Address> findByUserIdAndStatus(Long id, Integer status, Pageable pageable);

    Optional<Address> findByIdAndStatus(Long id, Integer status);

    Optional<Address> findByIdAndUserId(Long id, Long userId);

}
