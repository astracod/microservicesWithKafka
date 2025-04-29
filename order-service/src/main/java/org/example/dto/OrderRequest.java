package org.example.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class OrderRequest {
    @NotBlank
    private String productName;

    @DecimalMin("0.01")
    private BigDecimal productPrice;

    @Min(1)
    private int quantity;

    @NotNull
    private UUID userId;
}
