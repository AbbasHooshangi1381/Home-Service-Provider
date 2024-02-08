package com.example.springbootfinal.repository;

import com.example.springbootfinal.domain.userEntity.Admin;
import com.example.springbootfinal.domain.userEntity.Expert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface ExpertRepository extends JpaRepository<Expert,Integer> {
     boolean existsByEmail(String email);
     Optional<Expert> findByEmail(String email);
     Optional<Expert> findByUserNameAndPassword(String username, String password);

     @Query("SELECT s FROM Suggestion s WHERE s.customerOrder.id = :orderId ORDER BY s.expert.stars DESC")
     List<Expert> findExpertsByOrderIdOrderByRatingDesc(@Param("orderId") Integer orderId);
}
