package com.example.springbootfinal.repository;

import com.example.springbootfinal.domain.userEntity.Expert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ExpertRepository extends JpaRepository<Expert,Integer> {

     boolean existsByEmail(String email);

    // Boolean changeStatus(Integer id);

  //   Boolean changePassword(Integer id , String newPassword);

}
