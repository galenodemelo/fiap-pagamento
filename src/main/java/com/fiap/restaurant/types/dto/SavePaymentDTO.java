package com.fiap.restaurant.types.dto;

import java.math.BigDecimal;

public class SavePaymentDTO {

    private BigDecimal value;

    private Long customerId;

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}
