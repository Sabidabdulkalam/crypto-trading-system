package com.crypto.trading_system.service;

import com.crypto.trading_system.dto.TradeRequest;
import com.crypto.trading_system.model.*;
import com.crypto.trading_system.repository.*;
import lombok.RequiredArgsConstructor;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TradeService {

    private final PriceRepository priceRepository;
    private final TradeRepository tradeRepository;
    private final WalletRepository walletRepository;

    @Transactional
    public Trade executeTrade(TradeRequest request) {
        String symbol = request.getSymbol().toUpperCase();
        String type = request.getType().toUpperCase();
        double quantity = request.getQuantity();

        if (!symbol.equals("BTCUSDT") && !symbol.equals("ETHUSDT")) {
            throw new IllegalArgumentException("Only BTCUSDT and ETHUSDT are supported.");
        }

        if (!type.equals("BUY") && !type.equals("SELL")) {
            throw new IllegalArgumentException("Trade type must be BUY or SELL.");
        }

        // Get latest price
        Price price = priceRepository.findTopBySymbolOrderByTimestampDesc(symbol);
        if (price == null) {
            throw new IllegalStateException("No price data available for " + symbol);
        }

        // Get wallet (ID = 1 assumed)
        Wallet wallet = walletRepository.findById(1L)
                .orElseThrow(() -> new IllegalStateException("Wallet not initialized."));

        double cryptoAmount = quantity;
        double cryptoPrice = (type.equals("BUY")) ? price.getAskPrice() : price.getBidPrice();
        double cost = cryptoPrice * quantity;

        // Perform trade logic
        if (type.equals("BUY")) {
            if (wallet.getUsdtBalance() < cost) {
                throw new IllegalStateException("Insufficient USDT balance.");
            }

            wallet.setUsdtBalance(wallet.getUsdtBalance() - cost);
            if (symbol.equals("BTCUSDT")) {
                wallet.setBtcBalance(wallet.getBtcBalance() + quantity);
            } else {
                wallet.setEthBalance(wallet.getEthBalance() + quantity);
            }

        } else if (type.equals("SELL")) {
            if (symbol.equals("BTCUSDT") && wallet.getBtcBalance() < quantity) {
                throw new IllegalStateException("Insufficient BTC balance.");
            }

            if (symbol.equals("ETHUSDT") && wallet.getEthBalance() < quantity) {
                throw new IllegalStateException("Insufficient ETH balance.");
            }

            wallet.setUsdtBalance(wallet.getUsdtBalance() + cost);
            if (symbol.equals("BTCUSDT")) {
                wallet.setBtcBalance(wallet.getBtcBalance() - quantity);
            } else {
                wallet.setEthBalance(wallet.getEthBalance() - quantity);
            }
        }

        walletRepository.save(wallet);

        Trade trade = Trade.builder()
                .symbol(symbol)
                .type(type)
                .quantity(quantity)
                .price(cryptoPrice)
                .timestamp(LocalDateTime.now())
                .build();

        return tradeRepository.save(trade);
    }

    public List<Trade> getAllTrades() {
        return tradeRepository.findAll();
    }
}
