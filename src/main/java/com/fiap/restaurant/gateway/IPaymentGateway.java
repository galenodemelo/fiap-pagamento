package com.fiap.restaurant.gateway;

import com.fiap.restaurant.entity.Payment;

public interface IPaymentGateway {

    Payment save(Payment payment);
}
