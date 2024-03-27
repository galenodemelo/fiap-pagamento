package com.fiap.restaurant.usecase;

import com.fiap.restaurant.entity.Payment;
import com.fiap.restaurant.gateway.IPaymentGateway;
import com.fiap.restaurant.types.dto.SavePaymentDTO;
import com.fiap.restaurant.types.exception.BusinessException;

import java.math.BigDecimal;
import java.util.Date;

public class PaymentUseCase {

    public static Payment save(SavePaymentDTO savePaymentDTO, IPaymentGateway paymentGateway) {
        Long orderId = savePaymentDTO.getOrderId();
        if (orderId == null) throw new BusinessException("Id do cliente não pode ser nulo");

        BigDecimal value = savePaymentDTO.getValue();
        if (value == null) throw new BusinessException("Valor não pode ser nulo");
        if (value.compareTo(BigDecimal.ZERO) <= 0.0) throw new BusinessException("Valor não pode ser zero ou negativo");

        Payment payment = new Payment();
        payment.setOrderId(orderId);
        payment.setValue(value);
        payment.setDateCreated(new Date());

        return paymentGateway.save(payment);
    }
}
