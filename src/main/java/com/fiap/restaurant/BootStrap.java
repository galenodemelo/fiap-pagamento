package com.fiap.restaurant;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.util.TableUtils;
import com.fiap.restaurant.external.db.idempotency.IdempotencyKey;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BootStrap {

    @Autowired
    private AmazonDynamoDB amazonDynamoDB;

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    @PostConstruct
    public void init() {
        createDynamoDBTables();
    }

    private void createDynamoDBTables() {
        final Class<?>[] dynamoTableList = new Class[]{IdempotencyKey.class};

        for (Class<?> dynamoTable : dynamoTableList) {
            CreateTableRequest tableRequest = dynamoDBMapper.generateCreateTableRequest(dynamoTable);
            tableRequest.setProvisionedThroughput(new ProvisionedThroughput(1L, 1L));

            TableUtils.createTableIfNotExists(amazonDynamoDB, tableRequest);
        }
    }
}
