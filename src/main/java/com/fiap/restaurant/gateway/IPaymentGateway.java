package com.fiap.restaurant.gateway;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fiap.restaurant.entity.Payment;

public interface IPaymentGateway {

    Payment save(Payment payment) throws JsonProcessingException;

    Payment fail(Payment payment) throws JsonProcessingException;
}
