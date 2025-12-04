package com.example.cryptoSystem.request;

public class PurchaseRequest {
    private String username;
    private String buySymbol;
    private String buyQty;

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getBuySymbol() {
        return buySymbol;
    }
    public void setBuySymbol(String buySymbol) {
        this.buySymbol = buySymbol;
    }
    public String getBuyQty() {
        return buyQty;
    }
    public void setBuyQty(String buyQty) {
        this.buyQty = buyQty;
    }
}
