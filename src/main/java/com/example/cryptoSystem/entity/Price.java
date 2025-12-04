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
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String symbol;
    private String bidPrice;
    private String bidQty;
    private String askPrice;
    private String askQty;

    public Price(String symbol, String bidPrice, String bidQty, String askPrice, String askQty) {
        this.symbol = symbol;
        this.bidPrice = bidPrice;
        this.bidQty = bidQty;
        this.askPrice = askPrice;
        this.askQty = askQty;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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
