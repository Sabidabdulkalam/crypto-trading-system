package com.crypto.trading_system.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Trade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String symbol;   // BTCUSDT or ETHUSDT
    private String type;     // BUY or SELL
    private double quantity;
    private double price;    // price per unit
    private LocalDateTime timestamp;
}
