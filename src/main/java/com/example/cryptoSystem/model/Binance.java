package com.example.cryptoSystem.model;

public class Binance {
    private String symbol;
    private String bidPrice;
    private String bidQty;
    private String askPrice;
    private String askQty;

    public Binance(String symbol, String bidPrice, String bidQty, String askPrice, String askQty) {
        this.symbol = symbol;
        this.bidPrice = bidPrice;
        this.bidQty = bidQty;
        this.askPrice = askPrice;
        this.askQty = askQty;
    }

    public String getSymbol() {
        return symbol;
    }
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getBidPrice() {
        return bidPrice;
    }
    public void setBidPrice(String bidPrice) {
        this.bidPrice = bidPrice;
    }

    public String getBidQty() {
        return bidQty;
    }
    public void setBidQty(String bidQty) {
        this.bidQty = bidQty;
    }

    public String getAskPrice() {
        return askPrice;
    }
    public void setAskPrice(String askPrice) {
        this.askPrice = askPrice;
    }

    public String getAskQty() {
        return askQty;
    }
    public void setAskQty(String askQty) {
        this.askQty = askQty;
    }

}
