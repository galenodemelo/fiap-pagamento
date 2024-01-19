package com.fiap.restaurant.external.db.idempotency;

import com.fiap.restaurant.entity.IdempotencyKey;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

@EnableScan
public interface IdempotencyKeyRepository extends CrudRepository<IdempotencyKey, String> {

    Optional<IdempotencyKey> findById(String id);
}
