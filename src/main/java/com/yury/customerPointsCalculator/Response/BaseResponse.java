package com.yury.customerPointsCalculator.Response;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class BaseResponse {

    private HttpStatus status;

}
