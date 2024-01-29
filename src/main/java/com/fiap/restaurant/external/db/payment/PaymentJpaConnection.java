package com.fiap.restaurant.external.db.payment;

import com.fiap.restaurant.types.db.PaymentDatabaseConnection;
import org.springframework.stereotype.Component;

@Component
public class PaymentJpaConnection implements PaymentDatabaseConnection<PaymentJpa> {

    private final PaymentRepository paymentRepository;

    public PaymentJpaConnection(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public PaymentJpa save(PaymentJpa payment) {
        return this.paymentRepository.save(payment);
    }
}
