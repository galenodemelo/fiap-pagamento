package com.fiap.restaurant.types.db;

import com.fiap.restaurant.external.db.payment.PaymentJpa;

public interface PaymentDatabaseConnection<T> {

    PaymentJpa save(T payment);
}
