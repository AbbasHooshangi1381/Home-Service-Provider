package com.example.springbootfinal.repository;

import com.example.springbootfinal.domain.serviceEntity.Duty;
import com.example.springbootfinal.domain.serviceEntity.SubDuty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface DutyRepository extends JpaRepository<Duty,Integer> {
    Optional<Duty> findByName(String name);
}
