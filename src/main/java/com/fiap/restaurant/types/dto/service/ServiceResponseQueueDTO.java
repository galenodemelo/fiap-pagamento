package com.fiap.restaurant.types.dto.service;

import java.io.Serializable;
import java.util.Map;

public record ServiceResponseQueueDTO(
    ServiceResponseQueueType type,
    Map<String, Serializable> data
) {
}
