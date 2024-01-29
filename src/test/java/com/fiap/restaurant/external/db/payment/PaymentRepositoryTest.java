package com.fiap.restaurant.external.db.payment;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class PaymentRepositoryTest {

    @Autowired
    PaymentRepository paymentRepository;

    @Test
    void mustSavePayment() {
        PaymentJpa paymentJpa = new PaymentJpa();
        paymentJpa.setCustomerId(99999L);
        paymentJpa.setValue(BigDecimal.TEN);
        paymentJpa.setDateCreated(new Date());

        PaymentJpa createdPaymentJpa = paymentRepository.save(paymentJpa);

        assertThat(createdPaymentJpa.getId()).isNotNull().isInstanceOf(UUID.class);
        assertThat(createdPaymentJpa.getCustomerId()).isEqualTo(paymentJpa.getCustomerId());
        assertThat(createdPaymentJpa.getValue()).isEqualTo(paymentJpa.getValue());
        assertThat(createdPaymentJpa.getDateCreated()).isEqualTo(paymentJpa.getDateCreated()).isBeforeOrEqualTo(new Date());
    }
}
