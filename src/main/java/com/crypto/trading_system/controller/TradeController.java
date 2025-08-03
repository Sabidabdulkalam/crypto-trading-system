package com.crypto.trading_system.controller;

import com.crypto.trading_system.dto.TradeRequest;
import com.crypto.trading_system.model.Trade;
import com.crypto.trading_system.service.TradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TradeController {

    private final TradeService tradeService;

    @PostMapping("/trade")
    public Trade trade(@RequestBody TradeRequest request) {
        return tradeService.executeTrade(request);
    }
}
