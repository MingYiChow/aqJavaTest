package com.example.cryptoSystem.repository;

import com.example.cryptoSystem.entity.TradeHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TradeHistoryRepository extends JpaRepository<TradeHistory, Long> {
    List<TradeHistory> findByUsername(String username);

}
