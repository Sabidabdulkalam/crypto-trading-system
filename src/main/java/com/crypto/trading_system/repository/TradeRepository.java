package com.crypto.trading_system.repository;

import com.crypto.trading_system.model.Trade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TradeRepository extends JpaRepository<Trade, Long> {
}
