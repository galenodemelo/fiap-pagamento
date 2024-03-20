package com.fiap.restaurant.gateway.queue;

import com.fiap.restaurant.controller.PaymentController;
import com.fiap.restaurant.types.db.PaymentDatabaseConnection;
import com.fiap.restaurant.types.dto.SavePaymentDTO;
import io.awspring.cloud.sqs.annotation.SqsListener;
import org.springframework.stereotype.Service;

@Service
public class PaymentInboundQueueGateway {

    private final PaymentDatabaseConnection paymentDatabaseConnection;

    public PaymentInboundQueueGateway(PaymentDatabaseConnection paymentDatabaseConnection) {
        this.paymentDatabaseConnection = paymentDatabaseConnection;
    }

    @SqsListener("payment-q")
    public void receiveMessage(SavePaymentDTO savePaymentDTO) {
        System.out.println("Recebendo mensagem de pagamento para o pedido " + savePaymentDTO.getOrderId());
        PaymentController.save(savePaymentDTO, paymentDatabaseConnection);
    }

    @SqsListener("payment-refund-q")
    public void receiveRefundMessage(SavePaymentDTO savePaymentDTO) {
        System.out.println("Recebendo mensagem de estorno para o pedido " + savePaymentDTO.getOrderId());
        PaymentController.refund(savePaymentDTO, paymentDatabaseConnection);
    }
}
