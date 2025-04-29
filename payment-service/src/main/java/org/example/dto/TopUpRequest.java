package org.example.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class TopUpRequest {
    private UUID userId;
    private BigDecimal amount;
}
