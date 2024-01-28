package com.example.springbootfinal.repository;

import com.example.springbootfinal.domain.serviceEntity.Duty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface DutyRepository extends JpaRepository<Duty,Integer> {
    Optional<Duty> findByName(String name);

}
