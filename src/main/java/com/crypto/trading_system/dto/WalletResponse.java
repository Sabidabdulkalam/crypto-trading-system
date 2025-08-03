package com.crypto.trading_system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WalletResponse {
    private double usdt;
    private double btc;
    private double eth;
}