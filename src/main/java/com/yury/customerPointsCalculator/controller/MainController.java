package com.yury.customerPointsCalculator.controller;

import com.yury.customerPointsCalculator.delegate.PointsDelegate;
import com.yury.customerPointsCalculator.response.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @Autowired
    private PointsDelegate pointsDelegate;

    @GetMapping("/customers/points")
    public ResponseEntity<BaseResponse> getCustomersPoints() {

        BaseResponse response = pointsDelegate.getPointsResponse(null);

        return new ResponseEntity(response, response.getStatus());
    }

    @GetMapping("/customers/{id}/points")
    public ResponseEntity<BaseResponse> getCustomersPointsByCustomerId(@PathVariable Long id) {

        BaseResponse response = pointsDelegate.getPointsResponse(id);

        return new ResponseEntity(response, response.getStatus());
    }

}