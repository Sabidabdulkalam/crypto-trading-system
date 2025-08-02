package com.crypto.trading_system.service;

import lombok.Data;
import java.util.List;

@Data
public class HuobiResponse {

    public List<HuobiTicker> data;
}
