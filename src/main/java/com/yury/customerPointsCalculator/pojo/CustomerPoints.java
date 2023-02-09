package com.yury.customerPointsCalculator.pojo;

import com.yury.customerPointsCalculator.entity.Customer;
import lombok.Data;

import java.util.Map;

@Data
public class CustomerPoints {

    private Customer customer;

    private long totalPoints;

    //key = month number, value = points
    private Map<String, Long> monthlyPointsMap;

}

