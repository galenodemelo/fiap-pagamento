package com.fiap.restaurant.external.db.idempotency;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
public class IdempotencyKeyJpaRepositoryTest {

    @Autowired
    IdempotencyKeyRepository idempotencyKeyRepository;

    @Autowired
    private AmazonDynamoDB amazonDynamoDB;

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    @Test
    void mustSaveIdempotencyKey() {
        IdempotencyKeyJpa idempotencyKeyJpa = new IdempotencyKeyJpa();
        idempotencyKeyJpa.setPayload("payload");
        idempotencyKeyJpa.setResponseBody("resposta");
        idempotencyKeyJpa.setResponseStatus(HttpStatus.OK.value());

        IdempotencyKeyJpa createdIdempotencyKeyJpa = idempotencyKeyRepository.save(idempotencyKeyJpa);

        assertThat(createdIdempotencyKeyJpa.getId()).isNotEmpty();
        assertThat(createdIdempotencyKeyJpa.getPayload()).isEqualTo(idempotencyKeyJpa.getPayload());
        assertThat(createdIdempotencyKeyJpa.getResponseBody()).isEqualTo(idempotencyKeyJpa.getResponseBody());
        assertThat(createdIdempotencyKeyJpa.getResponseStatus()).isEqualTo(idempotencyKeyJpa.getResponseStatus());
    }
}
