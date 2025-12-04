package com.example.cryptoSystem.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseResponse {
    private String message;
    private String walletBalance;
    private String walletSymbol;
    private String buyQty;
    private String buyPrice;
    private String buySymbol;

}
