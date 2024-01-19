package com.fiap.restaurant.external.db.idempotency;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.fiap.restaurant.entity.IdempotencyKey;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
public class IdempotencyKeyRepositoryTest {

    @Autowired
    IdempotencyKeyRepository idempotencyKeyRepository;

    @Autowired
    private AmazonDynamoDB amazonDynamoDB;

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    @Test
    void mustSaveIdempotencyKey() {
        IdempotencyKey idempotencyKey = new IdempotencyKey();
        idempotencyKey.setPayload("payload");
        idempotencyKey.setResponseBody("resposta");
        idempotencyKey.setResponseStatus(HttpStatus.OK.value());

        IdempotencyKey createdIdempotencyKey = idempotencyKeyRepository.save(idempotencyKey);

        assertThat(createdIdempotencyKey.getId()).isNotEmpty();
        assertThat(createdIdempotencyKey.getPayload()).isEqualTo(idempotencyKey.getPayload());
        assertThat(createdIdempotencyKey.getResponseBody()).isEqualTo(idempotencyKey.getResponseBody());
        assertThat(createdIdempotencyKey.getResponseStatus()).isEqualTo(idempotencyKey.getResponseStatus());
    }
}
