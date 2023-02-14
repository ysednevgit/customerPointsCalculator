package com.yury.customerPointsCalculator.response;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class BaseResponse {

    private HttpStatus status;

}
