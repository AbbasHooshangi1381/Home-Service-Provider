package com.example.springbootfinal.repository;

import com.example.springbootfinal.domain.other.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface WalletRepository extends JpaRepository<Wallet,Integer> {
    @Query("SELECT w.creditAmount FROM CustomerOrder co JOIN co.customer c JOIN c.wallet w WHERE co.id = :customerOrderId")
    Wallet findCreditAmountByCustomerOrderId(@Param("customerOrderId") Integer customerOrderId);

    @Query("SELECT w FROM Wallet w WHERE w.expert.id = :expertId")
    Optional<Wallet> findWalletByExpertId(@Param("expertId") Integer expertId);}
