package com.yury.customerPointsCalculator.Response;

import com.yury.customerPointsCalculator.pojo.CustomerPoints;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class GetPointsResponse extends BaseResponse {

    private List<CustomerPoints> customerPointsList = new ArrayList<>();
}
