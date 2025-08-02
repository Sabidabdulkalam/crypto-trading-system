package com.crypto.trading_system.service;

import lombok.Data;

@Data
public class BinanceTicker {

    public String symbol;
    public String bidPrice;
    public String askPrice;
}
