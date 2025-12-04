package com.example.cryptoSystem.services;

import com.example.cryptoSystem.entity.Price;
import com.example.cryptoSystem.entity.TradeHistory;
import com.example.cryptoSystem.entity.Wallet;
import com.example.cryptoSystem.repository.PriceRepository;
import com.example.cryptoSystem.repository.TradeHistoryRepository;
import com.example.cryptoSystem.repository.WalletRepository;
import com.example.cryptoSystem.request.SellRequest;
import com.example.cryptoSystem.response.SellResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class SellService {

    private final WalletRepository walletRepository;
    private final PriceRepository priceRepository;
    private final TradeHistoryRepository tradeHistoryRepository;

    public SellResponse sell (SellRequest sellRequest) {
        //Get user wallet
        Wallet wallets = walletRepository.findByUsername(sellRequest.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        //Get latest best price in database
        Price price = priceRepository.findBySymbol(sellRequest.getSellSymbol())
                .orElseThrow(() -> new RuntimeException("Best price not found"));

        try {
            BigDecimal bestSellPrice = new BigDecimal(price.getBidPrice());

            //Compute buy price
            BigDecimal sellQuantity = new BigDecimal(sellRequest.getSellQty());
            BigDecimal bidQuantity = new BigDecimal(price.getBidQty());
            BigDecimal sellPrices = (sellQuantity.divide(bidQuantity, 8, BigDecimal.ROUND_HALF_UP)).multiply(bestSellPrice);

            BigDecimal walletBalance = new BigDecimal(wallets.getWallet());
            wallets.setWallet(String.valueOf(walletBalance.add(sellPrices)));
            walletRepository.save(wallets);

            //Save new purchase record
            TradeHistory tradeHistory = new TradeHistory(
                    sellRequest.getUsername(),
                    wallets.getWallet(),
                    wallets.getSymbol(),
                    null,
                    null,
                    null,
                    sellRequest.getSellQty(),
                    String.valueOf(sellPrices),
                    sellRequest.getSellSymbol()

//                LocalDataTime.now()
            );

            tradeHistoryRepository.save(tradeHistory);

            return new SellResponse(
                    "Sell Successful.",
                    wallets.getWallet(),
                    wallets.getSymbol(),
                    sellRequest.getSellQty(),
                    String.valueOf(sellPrices),
                    sellRequest.getSellSymbol()
            );

        } catch (NumberFormatException e) {
            throw new RuntimeException("Sell price is 0");
        }

    }
}
