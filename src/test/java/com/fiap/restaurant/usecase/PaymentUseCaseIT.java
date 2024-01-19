package com.fiap.restaurant.usecase;

import com.fiap.restaurant.entity.Payment;
import com.fiap.restaurant.external.db.payment.PaymentJpa;
import com.fiap.restaurant.external.db.payment.PaymentRepository;
import com.fiap.restaurant.gateway.PaymentGateway;
import com.fiap.restaurant.types.db.PaymentDatabaseConnection;
import com.fiap.restaurant.types.dto.SavePaymentDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class PaymentUseCaseIT {

    @Autowired
    private PaymentDatabaseConnection paymentDatabaseConnection;

    @Autowired
    private PaymentRepository paymentRepository;

    private SavePaymentDTO savePaymentDTO;

    private PaymentGateway paymentGateway;

    @BeforeEach
    void setup() {
        savePaymentDTO = new SavePaymentDTO();
        savePaymentDTO.setCustomerId(9999L);
        savePaymentDTO.setValue(BigDecimal.TEN);

        paymentGateway = new PaymentGateway(paymentDatabaseConnection);
    }

    @Test
    void mustSavePayment() {
        Payment payment = PaymentUseCase.save(savePaymentDTO, paymentGateway);

        Optional<PaymentJpa> optionalPaymentJpa = paymentRepository.findById(payment.getId());

        assertThat(optionalPaymentJpa)
                .isPresent()
                .hasValueSatisfying(paymentJpa -> {
                    assertThat(paymentJpa.getId()).isNotNull();
                    assertThat(paymentJpa.getCustomerId()).isEqualTo(payment.getCustomerId());
                    assertThat(paymentJpa.getValue()).isEqualTo(payment.getValue());
                    assertThat(payment.getDateCreated()).isNotNull().isBeforeOrEqualTo(new Date());
                });
    }
}
