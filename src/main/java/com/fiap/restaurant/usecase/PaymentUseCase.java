package com.fiap.restaurant.usecase;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fiap.restaurant.entity.Payment;
import com.fiap.restaurant.gateway.IPaymentGateway;
import com.fiap.restaurant.types.dto.SavePaymentDTO;
import com.fiap.restaurant.types.exception.BusinessException;

import java.math.BigDecimal;
import java.util.Date;

public class PaymentUseCase {

    public static void save(SavePaymentDTO savePaymentDTO, IPaymentGateway paymentGateway) throws JsonProcessingException {
        Payment payment = buildPayment(savePaymentDTO);

        try {
            validatePayment(payment);
            paymentGateway.save(payment);
        } catch (Exception exception) {
            paymentGateway.fail(payment);
            System.out.println(exception.getMessage());
        }
    }

    public static void refund(SavePaymentDTO savePaymentDTO, IPaymentGateway paymentGateway) throws JsonProcessingException {
        Payment payment = buildPayment(savePaymentDTO);
        try {
            validatePayment(payment);
            paymentGateway.refund(payment);
        } catch (Exception exception) {
            paymentGateway.fail(payment);
            System.out.println(exception.getMessage());
        }
    }

    private static Payment buildPayment(SavePaymentDTO savePaymentDTO) {
        Payment payment = new Payment();
        payment.setOrderId(savePaymentDTO.getOrderId());
        payment.setValue(savePaymentDTO.getValue());
        payment.setDateCreated(new Date());

        return payment;
    }

    private static void validatePayment(Payment payment) {
        if (payment.getOrderId() == null) throw new BusinessException("Id do cliente não pode ser nulo");
        if (payment.getValue() == null) throw new BusinessException("Valor não pode ser nulo");
        if (payment.getValue().compareTo(BigDecimal.ZERO) <= 0.0) throw new BusinessException("Valor não pode ser zero ou negativo");
    }
}
