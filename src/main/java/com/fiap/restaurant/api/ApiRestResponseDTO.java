package com.fiap.restaurant.api;

import jakarta.annotation.Nullable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ApiRestResponseDTO {

    private Boolean success;
    private String message;

    private ApiRestResponseDTO() {}

    public static ResponseEntity<ApiRestResponseDTO> build(HttpStatus httpStatus, @Nullable String message) {
        ApiRestResponseDTO apiRestResponseDTO = new ApiRestResponseDTO();
        apiRestResponseDTO.setSuccess(httpStatus.is2xxSuccessful());
        apiRestResponseDTO.setMessage(message);

        return new ResponseEntity<>(apiRestResponseDTO, httpStatus);
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
