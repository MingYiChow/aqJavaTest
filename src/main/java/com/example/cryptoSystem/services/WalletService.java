package com.example.cryptoSystem.services;

import com.example.cryptoSystem.entity.Wallet;
import com.example.cryptoSystem.repository.WalletRepository;
import com.example.cryptoSystem.response.WalletResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class WalletService {
    private final WalletRepository walletRepository;

    public WalletResponse getBalance (String username) {
        Wallet wallet = walletRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Wallet not found!!"));

        return new WalletResponse(
                wallet.getUsername(),
                wallet.getWallet()
        );
    }
}
