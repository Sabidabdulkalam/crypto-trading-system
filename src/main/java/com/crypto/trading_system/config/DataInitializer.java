package com.crypto.trading_system.config;

import com.crypto.trading_system.model.Wallet;
import com.crypto.trading_system.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer  implements CommandLineRunner {

    private final WalletRepository walletRepository;

    @Override
    public void run(String... args) throws Exception {
        if (!walletRepository.existsById(1L)) {
            Wallet wallet = Wallet.builder()
                    .id(1L)
                    .usdtBalance(50000.0)
                    .btcBalance(0.0)
                    .ethBalance(0.0)
                    .build();

            walletRepository.save(wallet);
            System.out.println("✅ Wallet initialized with 50,000 USDT.");
        } else {
            System.out.println("ℹ️ Wallet already exists. Skipping initialization.");
        }
    }
}
