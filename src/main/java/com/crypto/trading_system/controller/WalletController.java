package com.crypto.trading_system.controller;

import com.crypto.trading_system.dto.WalletResponse;
import com.crypto.trading_system.model.Wallet;
import com.crypto.trading_system.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/wallet")
@RequiredArgsConstructor
public class WalletController {

    private final WalletRepository walletRepository;

    @GetMapping
    public WalletResponse getWalletBalance() {
        Wallet wallet = walletRepository.findById(1L)
                .orElseThrow(() -> new IllegalStateException("Wallet not found"));

        return new WalletResponse(
                wallet.getUsdtBalance(),
                wallet.getBtcBalance(),
                wallet.getEthBalance()
        );
    }
}
