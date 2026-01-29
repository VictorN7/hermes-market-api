package com.hermes.market.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hermes.market.domain.user.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
