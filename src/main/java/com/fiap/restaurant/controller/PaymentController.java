package com.fiap.restaurant.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fiap.restaurant.gateway.IPaymentGateway;
import com.fiap.restaurant.gateway.PaymentGateway;
import com.fiap.restaurant.types.db.PaymentDatabaseConnection;
import com.fiap.restaurant.types.dto.SavePaymentDTO;
import com.fiap.restaurant.usecase.PaymentUseCase;
import org.springframework.stereotype.Controller;

@Controller
public class PaymentController {

    public static void save(SavePaymentDTO savePaymentDTO, PaymentDatabaseConnection paymentDatabaseConnection) throws JsonProcessingException {
        IPaymentGateway paymentGateway = new PaymentGateway(paymentDatabaseConnection);
        PaymentUseCase.save(savePaymentDTO, paymentGateway);
    }

    public static void refund(SavePaymentDTO savePaymentDTO, PaymentDatabaseConnection paymentDatabaseConnection) throws JsonProcessingException {
        IPaymentGateway paymentGateway = new PaymentGateway(paymentDatabaseConnection);
        PaymentUseCase.refund(savePaymentDTO, paymentGateway);
    }
}
