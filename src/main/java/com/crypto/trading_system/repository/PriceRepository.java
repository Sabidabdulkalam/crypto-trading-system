package com.crypto.trading_system.repository;

import com.crypto.trading_system.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRepository extends JpaRepository<Price,Long> {
    Price findTopBySymbolOrderByTimestampDesc(String symbol);
}
