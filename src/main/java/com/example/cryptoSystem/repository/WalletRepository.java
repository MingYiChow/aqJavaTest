package com.example.cryptoSystem.repository;

import com.example.cryptoSystem.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WalletRepository extends JpaRepository<Wallet,Long> {
    Optional<Wallet> findByUsername(String username);
}
