package com.fiap.restaurant.usecase;

import com.fiap.restaurant.entity.Payment;
import com.fiap.restaurant.gateway.PaymentGateway;
import com.fiap.restaurant.types.db.PaymentDatabaseConnection;
import com.fiap.restaurant.types.dto.SavePaymentDTO;
import com.fiap.restaurant.types.exception.BusinessException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@ActiveProfiles("test")
class PaymentUseCaseTest {

    @Autowired
    private PaymentDatabaseConnection paymentDatabaseConnection;

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

        assertThat(payment.getId()).isNotNull().isInstanceOf(UUID.class);
        assertThat(payment.getCustomerId()).isEqualTo(savePaymentDTO.getCustomerId());
        assertThat(payment.getValue()).isEqualTo(savePaymentDTO.getValue());
        assertThat(payment.getDateCreated()).isNotNull().isBeforeOrEqualTo(new Date());
    }

    @Test
    void mustNotSavePayment_WhenCustomerIdIsNull() {
        savePaymentDTO.setCustomerId(null);

        assertThatThrownBy(() -> PaymentUseCase.save(savePaymentDTO, paymentGateway))
                .isInstanceOf(BusinessException.class)
                .hasMessage("Id do cliente não pode ser nulo");
    }

    @Test
    void mustNotSavePayment_WhenValueIsNull() {
        savePaymentDTO.setValue(null);

        assertThatThrownBy(() -> PaymentUseCase.save(savePaymentDTO, paymentGateway))
                .isInstanceOf(BusinessException.class)
                .hasMessage("Valor não pode ser nulo");
    }

    @Test
    void mustNotSavePayment_WhenValueIsZero() {
        savePaymentDTO.setValue(BigDecimal.ZERO);

        assertThatThrownBy(() -> PaymentUseCase.save(savePaymentDTO, paymentGateway))
                .isInstanceOf(BusinessException.class)
                .hasMessage("Valor não pode ser zero ou negativo");
    }
}
