package com.fiap.restaurant;

import com.fiap.restaurant.external.db.payment.PaymentJpa;
import com.fiap.restaurant.types.db.PaymentDatabaseConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Autowired
    public PaymentDatabaseConnection<PaymentJpa> paymentDatabaseConnection;
}
