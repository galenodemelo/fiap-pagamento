package com.fiap.restaurant.external.db.idempotency;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

@EnableScan
public interface IdempotencyKeyRepository extends CrudRepository<IdempotencyKeyJpa, String> {

    Optional<IdempotencyKeyJpa> findById(String id);
}
