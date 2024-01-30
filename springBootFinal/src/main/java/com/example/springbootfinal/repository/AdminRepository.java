package com.example.springbootfinal.repository;

import com.example.springbootfinal.domain.userEntity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Integer> {
    boolean existsByEmail(String email);
    Optional<Admin> findByUserNameAndPassword(String username, String password);
    Optional<Admin> findByEmail(String email);
}
