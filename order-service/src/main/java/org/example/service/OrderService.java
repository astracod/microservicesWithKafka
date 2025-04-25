package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.dto.OrderRequest;
import org.example.event.OrderEvent;
import org.example.model.Order;
import org.example.model.Product;
import org.example.repository.OrderRepository;
import org.example.repository.ProductRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;

    public Order createOrder(OrderRequest request) {
        // 1. Находим продукт
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // 2. Создаём заказ
        Order order = Order.builder()
                .product(product)
                .quantity(request.getQuantity())
                .status("CREATED")
                .build();

        order = orderRepository.save(order);

        // 3. Отправляем событие
        OrderEvent event = OrderEvent.builder()
                .correlationId(UUID.randomUUID().toString())
                .orderId(order.getId())
                .productId(product.getId())
                .productName(product.getName())
                .productPrice(product.getPrice())
                .quantity(order.getQuantity())
                .build();

        kafkaTemplate.send("order-events", event);

        return order;
    }
    }
}
