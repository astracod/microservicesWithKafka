package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.dto.OrderRequest;
import org.example.event.OrderEvent;
import org.example.model.Order;
import org.example.repository.OrderRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;
    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;

    @Transactional
    public Order createOrder(OrderRequest request) {
        Order order = new Order();
        order.setUserId(request.getUserId());
        order.setProductName(request.getProductName());
        order.setProductPrice(request.getProductPrice());
        order.setQuantity(request.getQuantity());

        Order savedOrder = orderRepository.save(order);

        // Отправка события в Kafka
        kafkaTemplate.send("order-events", OrderEvent.from(savedOrder));

        return savedOrder;
    }


}


