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
        System.out.println("Recebendo mensagem de pagamento" + savePaymentDTO);
        PaymentController.save(savePaymentDTO, paymentDatabaseConnection);
    }
}
