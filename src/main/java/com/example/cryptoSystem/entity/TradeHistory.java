package com.example.cryptoSystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TradeHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String username;
    private String wallet;
    private String symbol;
    private String BuyQty;
    private String BuyPrice;
    private String BuySymbol;
    private String SellQty;
    private String SellPrice;
    private String SellSymbol;

    public TradeHistory(String username, String wallet, String symbol, String BuyQty, String BuyPrice, String BuySymbol, String SellQty, String SellPrice, String SellSymbol) {
        this.username = username;
        this.wallet = wallet;
        this.symbol = symbol;
        this.BuyQty = BuyQty;
        this.BuyPrice = BuyPrice;
        this.BuySymbol = BuySymbol;
        this.SellQty = SellQty;
        this.SellPrice = SellPrice;
        this.SellSymbol = SellSymbol;
    }
}
