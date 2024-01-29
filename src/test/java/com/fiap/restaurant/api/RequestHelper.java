package com.fiap.restaurant.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;

public class RequestHelper {

    public static final String ENDPOINT_HOST = "http://localhost:8081";
    public static final ContentType DEFAULT_CONTENT_TYPE = ContentType.JSON;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String asJsonString(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }
}
