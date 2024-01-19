package com.fiap.restaurant.external.db.payment;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface PaymentRepository extends CrudRepository<PaymentJpa, String> {
}
