package com.example.springbootfinal.repository;

import com.example.springbootfinal.domain.other.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface WalletRepository extends JpaRepository<Wallet,Integer> {
}
