package com.fiap.restaurant.types.dto;

import java.math.BigDecimal;

public class SavePaymentDTO {

    private BigDecimal value;

    private Long orderId;

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
