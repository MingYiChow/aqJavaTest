package com.example.cryptoSystem;

import com.example.cryptoSystem.services.PriceService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class PriceScheduler {

    private final PriceService priceService;

    public PriceScheduler(PriceService priceService) {
        this.priceService = priceService;
    }

    @Scheduled(fixedRate = 10000)
    public void updatePricesEvery10Seconds(){
        System.out.println("Updating prices every 10 seconds.....");
        priceService.updateBestPrice();

    }
}
