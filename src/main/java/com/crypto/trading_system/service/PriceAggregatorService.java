package com.crypto.trading_system.service;

import com.crypto.trading_system.model.Price;
import com.crypto.trading_system.repository.PriceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class PriceAggregatorService {

    private final PriceRepository priceRepository;
    private final RestTemplate restTemplate = new RestTemplate();

    private static final String BINANCE_URL = "https://api.binance.com/api/v3/ticker/bookTicker";
    private static final String HUOBI_URL = "https://api.huobi.pro/market/tickers";

    @Scheduled(fixedRate = 10000) // every 10 seconds
    public void fetchAndSavePrices() {
        log.info("Fetching prices...");

        Map<String, Double[]> binance = fetchFromBinance();
        Map<String, Double[]> huobi = fetchFromHuobi();

        for (String symbol : List.of("BTCUSDT", "ETHUSDT")) {
            try {
                double binAsk = binance.get(symbol)[0];
                double binBid = binance.get(symbol)[1];
                double huoAsk = huobi.get(symbol)[0];
                double huoBid = huobi.get(symbol)[1];

                double bestAsk = Math.min(binAsk, huoAsk); // Buy
                double bestBid = Math.max(binBid, huoBid); // Sell

                Price price = Price.builder()
                        .symbol(symbol)
                        .askPrice(bestAsk)
                        .bidPrice(bestBid)
                        .timestamp(LocalDateTime.now())
                        .build();

                priceRepository.save(price);
                log.info("Saved best price for {}: Ask={}, Bid={}", symbol, bestAsk, bestBid);

            } catch (Exception e) {
                log.error("Error saving price for {}", symbol, e);
            }
        }
    }

    private Map<String, Double[]> fetchFromBinance() {
        Map<String, Double[]> map = new HashMap<>();
        try {
            BinanceTicker[] tickers = restTemplate.getForObject(BINANCE_URL, BinanceTicker[].class);
            for (BinanceTicker t : tickers) {
                if (t.symbol.equals("BTCUSDT") || t.symbol.equals("ETHUSDT")) {
                    map.put(t.symbol, new Double[]{
                            Double.parseDouble(t.askPrice),
                            Double.parseDouble(t.bidPrice)
                    });
                }
            }
        } catch (Exception e) {
            log.error("Failed to fetch from Binance", e);
        }
        return map;
    }

    private Map<String, Double[]> fetchFromHuobi() {
        Map<String, Double[]> map = new HashMap<>();
        try {
            HuobiResponse response = restTemplate.getForObject(HUOBI_URL, HuobiResponse.class);
            for (HuobiTicker t : response.getData()) {
                if (t.symbol.equalsIgnoreCase("btcusdt") || t.symbol.equalsIgnoreCase("ethusdt")) {
                    map.put(t.symbol.toUpperCase(), new Double[]{t.ask, t.bid});
                }
            }
        } catch (Exception e) {
            log.error("Failed to fetch from Huobi", e);
        }
        return map;
    }
}
