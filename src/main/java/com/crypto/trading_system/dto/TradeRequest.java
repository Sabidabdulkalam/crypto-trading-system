package com.crypto.trading_system.dto;

import lombok.Data;

@Data
public class TradeRequest {

    private String symbol;   // e.g., BTCUSDT or ETHUSDT
    private String type;     // BUY or SELL
    private double quantity; // How much crypto to buy or sell

}
