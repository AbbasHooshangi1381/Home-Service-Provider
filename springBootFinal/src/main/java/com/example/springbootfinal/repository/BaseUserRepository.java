package com.example.springbootfinal.repository;

import com.example.springbootfinal.domain.userEntity.Admin;
import com.example.springbootfinal.domain.userEntity.BaseUser;
import com.example.springbootfinal.domain.userEntity.Expert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Repository
public interface BaseUserRepository extends JpaRepository<BaseUser,Integer>, JpaSpecificationExecutor<BaseUser> {
    Optional<BaseUser>findByUserName(String userName);
    Optional<BaseUser>findByEmail(String email);
    @Transactional
    @Modifying
    @Query("UPDATE BaseUser a SET a.enabled=true WHERE a.email=?1")
    int enableAppUser(String email);

}
