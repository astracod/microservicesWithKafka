package org.example.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class OrderRequest {
    @NotBlank(message = "Product name cannot be empty")
    private String productName;

    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity;
}
