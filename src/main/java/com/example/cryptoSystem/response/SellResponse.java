package com.example.cryptoSystem.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SellResponse {
    private String message;
    private String walletBalance;
    private String walletSymbol;
    private String sellQty;
    private String sellPrice;
    private String sellSymbol;
}
