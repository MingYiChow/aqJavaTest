package com.example.cryptoSystem.controller;

import com.example.cryptoSystem.entity.Price;
import com.example.cryptoSystem.entity.TradeHistory;
import com.example.cryptoSystem.request.PurchaseRequest;
import com.example.cryptoSystem.request.SellRequest;
import com.example.cryptoSystem.response.PurchaseResponse;
import com.example.cryptoSystem.response.SellResponse;
import com.example.cryptoSystem.response.WalletResponse;
import com.example.cryptoSystem.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/crypto")
@RequiredArgsConstructor
public class CryptoController {

    private final PriceService priceService;
    private final PurchaseService purchaseService;
    private final WalletService walletService;
    private final SellService sellService;
    private final TradeHistoryService tradeHistoryService;

    @GetMapping("/getAllBestPrices")
    public List<Price> getAllBestPrices() {
        return priceService.getAllBestPrices();
    }

    @PostMapping(value = "/purchase", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public PurchaseResponse purchaseAsk(@RequestBody PurchaseRequest purchaseRequest) {
        return purchaseService.purchase(purchaseRequest);
    }

    @PostMapping(value = "/sell", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public SellResponse sellBid(@RequestBody SellRequest sellRequest) {
        return sellService.sell(sellRequest);
    }

    @GetMapping(value = "/{username}/balance", produces = MediaType.APPLICATION_JSON_VALUE)
    public WalletResponse getBalance(@PathVariable String username) {
        return walletService.getBalance(username);
    }

    @GetMapping(value = "/{username}/tradeHistory", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TradeHistory> getTradeHistory(@PathVariable String username) {
        return tradeHistoryService.getUserTradeHistory(username);
    }
}
