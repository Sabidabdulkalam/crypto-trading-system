package com.crypto.trading_system.service;

import lombok.Data;

@Data
public class HuobiTicker {

    public String symbol;
    public double bid;
    public double ask;
}
