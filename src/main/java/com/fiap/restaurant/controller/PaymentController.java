package com.fiap.restaurant.controller;

import com.fiap.restaurant.gateway.IPaymentGateway;
import com.fiap.restaurant.gateway.PaymentGateway;
import com.fiap.restaurant.types.db.PaymentDatabaseConnection;
import com.fiap.restaurant.types.dto.SavePaymentDTO;
import com.fiap.restaurant.usecase.PaymentUseCase;

public class PaymentController {

    public static void save(SavePaymentDTO savePaymentDTO, PaymentDatabaseConnection paymentDatabaseConnection) {
        IPaymentGateway paymentGateway = new PaymentGateway(paymentDatabaseConnection);
        PaymentUseCase.save(savePaymentDTO, paymentGateway);
    }
}
