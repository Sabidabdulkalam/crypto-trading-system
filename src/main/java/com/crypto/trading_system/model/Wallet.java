package com.crypto.trading_system.model;

import jakarta.persistence.Entity;
import lombok.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Wallet {

    @Id
    private Long id;  // only one wallet for now, so use fixed ID = 1

    private double usdtBalance;
    private double btcBalance;
    private double ethBalance;
}
