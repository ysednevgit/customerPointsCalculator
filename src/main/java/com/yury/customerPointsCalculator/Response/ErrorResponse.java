package com.yury.customerPointsCalculator.Response;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ErrorResponse extends BaseResponse {

    private String message;

    public ErrorResponse(HttpStatus status, String message) {
        setStatus(status);
        setMessage(message);
    }
}
