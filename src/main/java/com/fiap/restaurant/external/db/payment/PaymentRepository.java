package com.fiap.restaurant.external.db.payment;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

@EnableScan
public interface PaymentRepository extends CrudRepository<PaymentJpa, String> {

    Optional<PaymentJpa> findById(UUID id);
}
