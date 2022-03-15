package com.sportyshoes.controllers.advice;


import com.sportyshoes.exceptions.ProductNotFoundException;
import com.sportyshoes.models.ErrorDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionControllerAdvice {

  @ExceptionHandler(ProductNotFoundException.class)
  public ResponseEntity<ErrorDetails> exceptionNotEnoughMoneyHandler() {
    ErrorDetails errorDetails = new ErrorDetails();
    errorDetails.setMessage("Not enough money to make the payment.");
    return ResponseEntity
        .badRequest()
        .body(errorDetails);
  }
}
