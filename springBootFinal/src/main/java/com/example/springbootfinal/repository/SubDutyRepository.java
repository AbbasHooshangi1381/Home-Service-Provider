package com.example.springbootfinal.repository;

import com.example.springbootfinal.domain.serviceEntity.SubDuty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubDutyRepository extends JpaRepository<SubDuty, Integer> {

    // boolean existsBySubServiceName(String name);
    Optional<SubDuty> findBySubServiceName(String subServiceName);


}
