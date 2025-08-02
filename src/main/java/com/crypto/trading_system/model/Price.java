package com.crypto.trading_system.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String symbol; // BTCUSDT or ETHUSDT

    private Double bidPrice; // for selling
    private Double askPrice; // for buying

    private LocalDateTime timestamp;
}