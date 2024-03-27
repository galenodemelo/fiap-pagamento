package com.fiap.restaurant.api;

import com.fiap.restaurant.types.db.PaymentDatabaseConnection;
import com.fiap.restaurant.types.dto.SavePaymentDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;

import static com.fiap.restaurant.api.RequestHelper.asJsonString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
class PaymentRestControllerTest {

    @Autowired
    private PaymentDatabaseConnection paymentDatabaseConnection;

    private MockMvc mockMvc;

    private SavePaymentDTO savePaymentDTO;

    private AutoCloseable openMocks;

    @BeforeEach
    void setup() {
        openMocks = MockitoAnnotations.openMocks(this);
        PaymentRestController paymentRestController = new PaymentRestController(paymentDatabaseConnection);
        mockMvc = MockMvcBuilders.standaloneSetup(paymentRestController).build();

        savePaymentDTO = new SavePaymentDTO();
        savePaymentDTO.setOrderId(9999L);
        savePaymentDTO.setValue(BigDecimal.TEN);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void mustSavePayment() throws Exception {
        mockMvc.perform(
                post("/payment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(savePaymentDTO))
        ).andExpect(status().isOk());
    }
}
