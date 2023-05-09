package com.bsuir.diploma.recycleappbackend.repository;

import com.bsuir.diploma.recycleappbackend.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByEmailAndIdIsNot(String email, Long clientId);

    boolean existsByUsername(String username);

    boolean existsByUsernameAndIdIsNot(String username, Long clientId);

}
