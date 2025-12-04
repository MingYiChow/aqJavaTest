package com.example.cryptoSystem.request;

public class SellRequest {
    private String username;
    private String sellSymbol;
    private String sellQty;

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getSellSymbol() {
        return sellSymbol;
    }
    public void setSellSymbol(String sellSymbol) {
        this.sellSymbol = sellSymbol;
    }
    public String getSellQty() {
        return sellQty;
    }
    public void setSellQty(String sellQty) {
        this.sellQty = sellQty;
    }
}
