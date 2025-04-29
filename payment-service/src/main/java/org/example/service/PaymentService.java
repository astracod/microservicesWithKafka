package org.example.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.model.PaymentCompletedEvent;
import org.example.model.PaymentFailedEvent;
import org.example.model.ReservePaymentEvent;
import org.example.model.UserBalance;
import org.example.repository.UserBalanceRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentService {
    private final UserBalanceRepository userBalanceRepository;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @KafkaListener(topics = "reserve-payment")
    public void handleReservePayment(ReservePaymentEvent event) {
        UserBalance user = userBalanceRepository.findById(event.getUserId())
                .orElseGet(() -> new UserBalance(event.getUserId(), BigDecimal.ZERO));

        if (user.getBalance().compareTo(event.getAmount()) >= 0) {
            user.setBalance(user.getBalance().subtract(event.getAmount()));
            userBalanceRepository.save(user);
            kafkaTemplate.send("payment-completed", new PaymentCompletedEvent(event.getOrderId()));
            log.info("Оплата завершена для заказа: {}", event.getOrderId());
        } else {
            kafkaTemplate.send("payment-failed", new PaymentFailedEvent(event.getOrderId(), "Недостаточно средств"));
            log.warn("Оплата не удалась для заказа: {}", event.getOrderId());
        }
    }

    public void topUpBalance(UUID userId, BigDecimal amount) {
        UserBalance user = userBalanceRepository.findById(userId)
                .orElse(new UserBalance(userId, BigDecimal.ZERO));
        user.setBalance(user.getBalance().add(amount));
        userBalanceRepository.save(user);
        log.info("Баланс пользователя {} пополнен на {}", userId, amount);
    }
}
