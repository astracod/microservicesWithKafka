package org.example.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.model.Order;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderEvent {
    private Long orderId;
    private UUID userId;
    private String productName;
    private BigDecimal productPrice;
    private int quantity;
    private String status;
    private Instant eventTime = Instant.now();

    public static OrderEvent from(Order order) {
        return OrderEvent.builder()
                .orderId(order.getId())
                .userId(order.getUserId())
                .productName(order.getProductName())
                .productPrice(order.getProductPrice())
                .quantity(order.getQuantity())
                .status(order.getStatus())
                .build();
    }
}
