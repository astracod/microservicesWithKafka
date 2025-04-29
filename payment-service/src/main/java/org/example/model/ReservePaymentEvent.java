package org.example.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class ReservePaymentEvent {
    private UUID orderId;
    private UUID userId;
    private BigDecimal amount;
}
