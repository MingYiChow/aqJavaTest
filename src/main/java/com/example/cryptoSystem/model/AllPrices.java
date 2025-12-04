package com.example.cryptoSystem.model;

public class AllPrices {

    private String symbol;
    private String bidPrice;
    private String bidQty;
    private String bestBidPrice;
    private String askPrice;
    private String askQty;
    private String bestAskPrice;

    public AllPrices(String symbol,
                     String bidPrice,
                     String bidQty,
                     String bestBidPrice,
                     String askPrice,
                     String askQty,
                     String bestAskPrice) {
        this.symbol = symbol;
        this.bidPrice = bidPrice;
        this.bidQty = bidQty;
        this.bestBidPrice = bestBidPrice;
        this.askPrice = askPrice;
        this.askQty = askQty;
        this.bestAskPrice = bestAskPrice;
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
    public String getBestBidPrice() {
        return bestBidPrice;
    }
    public void setBestBidPrice(String bestBidPrice) {
        this.bestBidPrice = bestBidPrice;
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
    public String getBestAskPrice() {
        return bestAskPrice;
    }
    public void setBestAskPrice(String bestAskPrice) {
        this.bestAskPrice = bestAskPrice;
    }
}
