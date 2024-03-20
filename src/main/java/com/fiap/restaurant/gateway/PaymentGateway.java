package com.fiap.restaurant.gateway;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.restaurant.entity.Payment;
import com.fiap.restaurant.external.db.payment.PaymentJpa;
import com.fiap.restaurant.external.messagebroker.MessageBroker;
import com.fiap.restaurant.external.messagebroker.SqsMessageBroker;
import com.fiap.restaurant.types.db.PaymentDatabaseConnection;
import com.fiap.restaurant.types.dto.service.ServiceResponseQueueDTO;
import com.fiap.restaurant.types.dto.service.ServiceResponseQueueType;
import com.fiap.restaurant.types.mapper.PaymentMapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unchecked")
public class PaymentGateway implements IPaymentGateway {

    private final PaymentDatabaseConnection paymentDatabaseConnection;

    private final MessageBroker messageBroker;

    public PaymentGateway(PaymentDatabaseConnection paymentDatabaseConnection) {
        this.paymentDatabaseConnection = paymentDatabaseConnection;
        this.messageBroker = new SqsMessageBroker("response-q");
    }

    @Override
    @Transactional
    public Payment save(Payment payment) throws JsonProcessingException {
        PaymentJpa paymentJpa = new PaymentJpa();
        paymentJpa.setOrderId(payment.getOrderId());
        paymentJpa.setValue(payment.getValue());

        paymentJpa = this.paymentDatabaseConnection.save(paymentJpa);

        String jsonData = toJson(new ServiceResponseQueueDTO(
            ServiceResponseQueueType.ORDER_PAYMENT_FINISHED,
            Map.of("orderId", payment.getOrderId())
        ));

        messageBroker.send(jsonData);

        return PaymentMapper.INSTANCE.toPayment(paymentJpa);
    }

    @Override
    public Payment fail(Payment payment) throws JsonProcessingException {
        String jsonData = toJson(new ServiceResponseQueueDTO(
                ServiceResponseQueueType.ORDER_PAYMENT_FAILED,
                Map.of("orderId", payment.getOrderId())
        ));

        messageBroker.send(jsonData);
        return null;
    }

    private String toJson(ServiceResponseQueueDTO data) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(data);
    }
}
