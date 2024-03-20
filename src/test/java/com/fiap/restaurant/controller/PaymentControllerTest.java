package com.fiap.restaurant.controller;

import com.fiap.restaurant.types.db.PaymentDatabaseConnection;
import com.fiap.restaurant.types.dto.SavePaymentDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class PaymentControllerTest {

    @Autowired
    private PaymentDatabaseConnection paymentDatabaseConnection;

    private SavePaymentDTO savePaymentDTO;

    @BeforeEach
    void setup() {
        savePaymentDTO = new SavePaymentDTO();
        savePaymentDTO.setOrderId(9999L);
        savePaymentDTO.setValue(BigDecimal.TEN);
    }

    @Test
    void mustSavePayment() {
        assertDoesNotThrow(() -> PaymentController.save(savePaymentDTO, paymentDatabaseConnection));
    }
}
