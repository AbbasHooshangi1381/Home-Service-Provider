package com.example.springbootfinal.repository;

import com.example.springbootfinal.domain.other.CustomerOrder;
import com.example.springbootfinal.domain.serviceEntity.SubDuty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubDutyRepository extends JpaRepository<SubDuty, Integer> {
    Optional<SubDuty> findBySubServiceName(String subServiceName);
    List<SubDuty> findByServiceId(Integer dutyId);

    @Query("SELECT s FROM SubDuty s WHERE s.service.name = :serviceName")
    List<SubDuty> findSubDutyByServiceName(@Param("serviceName") String serviceName);

    Optional<SubDuty>findByDescription(String description);
    Optional<SubDuty>findByPrice(Double price);


}
