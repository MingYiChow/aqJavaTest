package com.example.cryptoSystem.services;

import com.example.cryptoSystem.entity.Price;
import com.example.cryptoSystem.entity.TradeHistory;
import com.example.cryptoSystem.entity.Wallet;
import com.example.cryptoSystem.repository.TradeHistoryRepository;
import com.example.cryptoSystem.request.PurchaseRequest;
import com.example.cryptoSystem.response.PurchaseResponse;
import com.example.cryptoSystem.repository.PriceRepository;
import com.example.cryptoSystem.repository.WalletRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class PurchaseService {

    private final WalletRepository walletRepository;
    private final PriceRepository priceRepository;
    private final TradeHistoryRepository tradeHistoryRepository;

    public PurchaseResponse purchase(PurchaseRequest purchaseRequest) {
        //Get user wallet
        Wallet wallets = walletRepository.findByUsername(purchaseRequest.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        //Get latest best price in database
        Price price = priceRepository.findBySymbol(purchaseRequest.getBuySymbol())
                .orElseThrow(() -> new RuntimeException("Best price not found"));

        BigDecimal bestBuyPrice = new BigDecimal(price.getAskPrice());

        //Compute buy price
        BigDecimal buyQuantity = new BigDecimal(purchaseRequest.getBuyQty());
        BigDecimal askQuantity = new BigDecimal(price.getAskQty());
        BigDecimal buyPrices = (buyQuantity.divide(askQuantity, 8, BigDecimal.ROUND_HALF_UP)).multiply(bestBuyPrice);

        //check wallet balance
        BigDecimal walletBalance = new BigDecimal(wallets.getWallet());
        if (walletBalance.subtract(buyPrices).compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Insufficient funds.");
        }

        wallets.setWallet(String.valueOf(walletBalance.subtract(buyPrices)));
        walletRepository.save(wallets);

        //Save new purchase record in Trade History
        TradeHistory tradeHistory = new TradeHistory(
                purchaseRequest.getUsername(),
                wallets.getWallet(),
                wallets.getSymbol(),
                purchaseRequest.getBuyQty(),
                String.valueOf(buyPrices),
                purchaseRequest.getBuySymbol(),
                null,
                null,
                null
//                LocalDataTime.now()
        );

        tradeHistoryRepository.save(tradeHistory);

        return new PurchaseResponse(
                "Purchase Successful.",
                wallets.getWallet(),
                wallets.getSymbol(),
                purchaseRequest.getBuyQty(),
                String.valueOf(buyPrices),
                purchaseRequest.getBuySymbol()
        );
    }

}
