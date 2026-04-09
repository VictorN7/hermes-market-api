package com.hermes.market.infrastructure.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.hermes.market.domain.user.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

    Page<User> findByStatus(Integer status, Pageable pageable);

    boolean existsByCpf(String cpf);

    boolean existsByEmail(String email);

    Optional<User> findByEmailIgnoreCase (String email);

}
