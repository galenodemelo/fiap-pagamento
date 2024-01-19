package com.fiap.restaurant.api;

import com.fiap.restaurant.controller.PaymentController;
import com.fiap.restaurant.types.db.PaymentDatabaseConnection;
import com.fiap.restaurant.types.dto.SavePaymentDTO;
import com.fiap.restaurant.types.exception.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentRestController {

    private final PaymentDatabaseConnection paymentDatabaseConnection;

    public PaymentRestController(PaymentDatabaseConnection paymentDatabaseConnection) {
        this.paymentDatabaseConnection = paymentDatabaseConnection;
    }

    @PostMapping
    public ResponseEntity<ApiRestResponseDTO> save(@RequestBody SavePaymentDTO savePaymentDTO) {
        try {
            PaymentController.save(savePaymentDTO, paymentDatabaseConnection);
            return ApiRestResponseDTO.build(HttpStatus.OK, "Cobrança registrada com sucesso");
        } catch (BusinessException businessException) {
            return ApiRestResponseDTO.build(HttpStatus.BAD_REQUEST, businessException.getMessage());
        }
    }
}
