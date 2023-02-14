package com.yury.customerPointsCalculator.exception;

import com.yury.customerPointsCalculator.response.ErrorResponse;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Data
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = CustomerNotFoundException.class)
    public ResponseEntity<Object> exception(CustomerNotFoundException exception) {

        ErrorResponse response = new ErrorResponse(HttpStatus.NOT_FOUND, exception.getMessage());

        log.error(exception.getMessage(), exception);

        return new ResponseEntity(response, response.getStatus());
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> exception(Exception exception) {

        ErrorResponse response = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error occurred, please try again later.");

        log.error(exception.getMessage(), exception);

        return new ResponseEntity(response, response.getStatus());
    }
}
