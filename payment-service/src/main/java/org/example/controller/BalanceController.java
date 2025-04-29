package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.TopUpRequest;
import org.example.service.PaymentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/balance")
public class BalanceController {
    private final PaymentService paymentService;

    @PostMapping("/top-up")
    public void topUpBalance(@RequestBody TopUpRequest request) {
        paymentService.topUpBalance(request.getUserId(), request.getAmount());
    }
}
