package com.yury.customerPointsCalculator.response;

import com.yury.customerPointsCalculator.entity.Customer;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CustomersResponse extends BaseResponse {

    private List<Customer> customers = new ArrayList<>();

}
