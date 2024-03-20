Feature: API - Payments

  Scenario: Save payment
    When saving a new payment
    Then must save the payment

  Scenario: Save an invalid payment
    When saving a new payment without order id
    Then must return an error message
