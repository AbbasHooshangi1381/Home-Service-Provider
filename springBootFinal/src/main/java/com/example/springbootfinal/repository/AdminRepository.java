package com.example.springbootfinal.repository;

import com.example.springbootfinal.domain.userEntity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Integer> {
    Optional<Admin> findByUserNameAndPassword(String username, String password);
    Optional<Admin> findByEmail(String email);
    Optional<Admin> findByPassword(String password);
}
