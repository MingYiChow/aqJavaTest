package com.example.cryptoSystem.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TradeHistoryResponse {
    private String username;
    private String wallet;
    private String symbol;
    private String BuyQty;
    private String BuyPrice;
    private String BuySymbol;
    private String SellQty;
    private String SellPrice;
    private String SellSymbol;
}
