package com.crypto.trading_system.repository;

import com.crypto.trading_system.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
}
