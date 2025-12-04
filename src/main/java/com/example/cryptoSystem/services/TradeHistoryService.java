package com.example.cryptoSystem.services;

import com.example.cryptoSystem.entity.TradeHistory;
import com.example.cryptoSystem.repository.TradeHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TradeHistoryService {
    private final TradeHistoryRepository tradeHistoryRepository;

    public List<TradeHistory> getUserTradeHistory (String username) {

        return tradeHistoryRepository.findByUsername(username);
    }
}
