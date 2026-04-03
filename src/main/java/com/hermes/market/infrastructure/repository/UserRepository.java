package com.hermes.market.infrastructure.repository;

import com.hermes.market.domain.user.Address;
import com.hermes.market.domain.user.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import com.hermes.market.domain.user.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

    List<User> findByStatus(UserStatus status);

}
