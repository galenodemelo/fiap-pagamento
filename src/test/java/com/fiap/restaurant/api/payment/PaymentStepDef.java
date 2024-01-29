package com.fiap.restaurant.api.payment;

import com.fiap.restaurant.types.dto.SavePaymentDTO;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;

import static com.fiap.restaurant.api.RequestHelper.DEFAULT_CONTENT_TYPE;
import static com.fiap.restaurant.api.RequestHelper.ENDPOINT_HOST;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.text.IsEmptyString.emptyOrNullString;

public class PaymentStepDef {

    private static final String ENDPOINT = ENDPOINT_HOST + "/payment";
    private static final String SCHEMA_LOCATION = "./schemas/api/payment";

    private static final String PAYMENT_SAVE_SCHEMA_LOCATION = SCHEMA_LOCATION + "/PaymentSaveSchema.json";
    private SavePaymentDTO savePaymentDTO;
    private Response response;

    @When("saving a new payment")
    public void savingANewPayment() {
        savePaymentDTO = new SavePaymentDTO();
        savePaymentDTO.setCustomerId(999999999L);
        savePaymentDTO.setValue(BigDecimal.TEN);

        response = given().contentType(DEFAULT_CONTENT_TYPE).body(savePaymentDTO)
                .when().post(ENDPOINT);
    }

    @Then("must save the payment")
    public void mustSaveThePayment() {
        response.then()
                .statusCode(HttpStatus.OK.value())
                .body(matchesJsonSchemaInClasspath(PAYMENT_SAVE_SCHEMA_LOCATION))
                .body("success", equalTo(true))
                .body("message", not(emptyOrNullString()));
    }

    @When("saving a new payment without customer id")
    public void savingANewPaymentWithoutCustomerId() {
        savePaymentDTO = new SavePaymentDTO();
        savePaymentDTO.setValue(BigDecimal.TEN);

        response = given().contentType(DEFAULT_CONTENT_TYPE).body(savePaymentDTO)
                .when().post(ENDPOINT + "/");
    }

    @Then("must return an error message")
    public void mustReturnAnErrorMessage() {
        response.then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body(matchesJsonSchemaInClasspath(PAYMENT_SAVE_SCHEMA_LOCATION))
                .body("$.success", equalTo(false))
                .body("$.message", not(emptyOrNullString()));
    }
}
