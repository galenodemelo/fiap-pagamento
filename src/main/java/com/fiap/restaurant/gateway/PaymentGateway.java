package com.fiap.restaurant.gateway;

import com.fiap.restaurant.entity.Payment;
import com.fiap.restaurant.external.db.payment.PaymentJpa;
import com.fiap.restaurant.types.db.PaymentDatabaseConnection;
import com.fiap.restaurant.types.mapper.PaymentMapper;

@SuppressWarnings("unchecked")
public class PaymentGateway implements IPaymentGateway {

    private final PaymentDatabaseConnection paymentDatabaseConnection;

    public PaymentGateway(PaymentDatabaseConnection paymentDatabaseConnection) {
        this.paymentDatabaseConnection = paymentDatabaseConnection;
    }

    @Override
    public Payment save(Payment payment) {
        PaymentJpa paymentJpa = new PaymentJpa();
        paymentJpa.setCustomerId(payment.getCustomerId());
        paymentJpa.setValue(payment.getValue());

        paymentJpa = this.paymentDatabaseConnection.save(paymentJpa);

        return PaymentMapper.INSTANCE.toPayment(paymentJpa);
    }
}
