package com.example.springbootfinal.repository;

import com.example.springbootfinal.domain.userEntity.Admin;
import com.example.springbootfinal.domain.userEntity.Expert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface ExpertRepository extends JpaRepository<Expert,Integer> {

     boolean existsByEmail(String email);

     Optional<Expert> findByEmail(String email);

     Optional<Expert> findByUserNameAndPassword(String username, String password);



}
