package com.crypto.trading_system.controller;

import com.crypto.trading_system.model.Price;
import com.crypto.trading_system.repository.PriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/prices")
@RequiredArgsConstructor
public class PriceController {

    private final PriceRepository priceRepository;

    @GetMapping("/{symbol}")
    public Price getLatestPrice(@PathVariable String symbol) {
        Price latestPrice = priceRepository.findTopBySymbolOrderByTimestampDesc(symbol.toUpperCase());

        if (latestPrice == null) {
            throw new IllegalArgumentException("No price data available for symbol: " + symbol);
        }

        return latestPrice;
    }
}
