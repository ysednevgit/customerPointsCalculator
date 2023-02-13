package com.yury.customerPointsCalculator.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yury.customerPointsCalculator.Response.BaseResponse;
import com.yury.customerPointsCalculator.Response.ErrorResponse;
import com.yury.customerPointsCalculator.delegate.PointsDelegate;
import com.yury.customerPointsCalculator.exception.CustomerNotFoundException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@ControllerAdvice
@Data
@Slf4j
public class MainController {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private PointsDelegate pointsDelegate;


    @GetMapping("/points")
    public ResponseEntity<BaseResponse> getPoints() {

        BaseResponse response = pointsDelegate.getPointsResponse(null);

        return new ResponseEntity(response, response.getStatus());
    }

    @GetMapping("/points/{id}")
    public ResponseEntity<BaseResponse> getPointsByCustomerId(@PathVariable Long id) {

        BaseResponse response = pointsDelegate.getPointsResponse(id);

        return new ResponseEntity(response, response.getStatus());
    }

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