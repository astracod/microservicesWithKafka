package org.example.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderEvent {
    private String correlationId;
    private Long orderId;
    private UUID productId;  // Теперь только ID продукта
    private String productName;  // + имя для удобства
    private BigDecimal productPrice;  // + цена
    private Integer quantity;
}
