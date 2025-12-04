package com.example.cryptoSystem.services;

import com.example.cryptoSystem.entity.Price;
import com.example.cryptoSystem.model.*;
import com.example.cryptoSystem.repository.PriceRepository;
import com.example.cryptoSystem.response.HuobiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.*;


@Service
@RequiredArgsConstructor
public class PriceService {

    private final RestTemplate restTemplate;
    private final PriceRepository priceRepository;

    private final String BINANCE_PRICE_URL = "https://api.binance.com/api/v3/ticker/bookTicker";
    private static final String HUOBI_PRICE_URL = "https://api.huobi.pro/market/tickers";

    private static final Set<String> SUPPORTED_SYMBOLS = Set.of("ETHUSDT", "BTCUSDT");

    public List<Price> getAllBestPrices() {
        return priceRepository.findAll();
    }

    public void updateBestPrice() {
        Binance[] binance = restTemplate.getForObject(BINANCE_PRICE_URL, Binance[].class);
        HuobiResponse huobi = restTemplate.getForObject(HUOBI_PRICE_URL, HuobiResponse.class);

        List<AllPrices> all = new ArrayList<>();

        //convert binance dto to all prices dto
        if (binance != null) {
            for (Binance dto : binance) {
                if (SUPPORTED_SYMBOLS.contains(dto.getSymbol())) {
                    BigDecimal getBestAskPrice = new BigDecimal(dto.getAskPrice());
                    BigDecimal getBestBidPrice = new BigDecimal(dto.getBidPrice());
                    BigDecimal getBestAskQty = new BigDecimal(dto.getAskQty());
                    BigDecimal getBestBidQty = new BigDecimal(dto.getBidQty());

                    BigDecimal getAskPrice = getBestAskPrice.divide(getBestAskQty, 8, BigDecimal.ROUND_HALF_UP);
                    BigDecimal getBidPrice = getBestBidPrice.divide(getBestBidQty, 8, BigDecimal.ROUND_HALF_UP);

                    all.add(new AllPrices(dto.getSymbol(), dto.getBidPrice(), dto.getBidQty(), String.valueOf(getBidPrice),
                            dto.getAskPrice(), dto.getAskQty(), String.valueOf(getAskPrice)));
                }
            }
        }

        //convert huobi dto to all prices dto
        if (huobi != null && (huobi.getData() != null)) {
            for (Data dto : huobi.getData()) {
                if (SUPPORTED_SYMBOLS.contains(dto.getSymbol())) {
                    BigDecimal getBestAskPrice = new BigDecimal(dto.getAsk());
                    BigDecimal getBestBidPrice = new BigDecimal(dto.getBid());
                    BigDecimal getBestAskQty = new BigDecimal(dto.getAskSize());
                    BigDecimal getBestBidQty = new BigDecimal(dto.getBidSize());

                    BigDecimal getAskPrice = getBestAskPrice.divide(getBestAskQty, 8, BigDecimal.ROUND_HALF_UP);
                    BigDecimal getBidPrice = getBestBidPrice.divide(getBestBidQty, 8, BigDecimal.ROUND_HALF_UP);

                    all.add(new AllPrices(dto.getSymbol(), dto.getBid(), dto.getBidSize(), String.valueOf(getBidPrice),
                            dto.getAsk(), dto.getAskSize(), String.valueOf(getAskPrice)));
                }
            }
        }

        if (all.isEmpty()) {
            System.out.println("No data found from both urls");
            return;
        }

        AllPrices bestBidPrices = all.stream()
                .max(Comparator.comparing(p -> new BigDecimal(p.getBestBidPrice())))
                .orElse(null);

        AllPrices bestAskPrices = all.stream()
                .min(Comparator.comparing(p -> new BigDecimal(p.getBestAskPrice())))
                .orElse(null);

        if (bestBidPrices != null || bestAskPrices != null) {
            //clear old records
            priceRepository.deleteAll();

            //store only best pricing
            priceRepository.save(new Price(bestBidPrices.getSymbol(), bestBidPrices.getBidPrice(), bestBidPrices.getBidQty(), "-", "-"));
            priceRepository.save(new Price(bestAskPrices.getSymbol(), "-", "-", bestAskPrices.getAskPrice(), bestAskPrices.getAskQty()));
        }

    }

}
