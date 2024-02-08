package com.example.springbootfinal.repository;

import com.example.springbootfinal.domain.userEntity.BaseUser;
import com.example.springbootfinal.domain.userEntity.Expert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BaseUserRepository extends JpaRepository<BaseUser,Integer>, JpaSpecificationExecutor<BaseUser> {
}
