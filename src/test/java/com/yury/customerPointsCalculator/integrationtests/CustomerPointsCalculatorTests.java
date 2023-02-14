package com.yury.customerPointsCalculator.integrationtests;

import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class CustomerPointsCalculatorTests {

    @Test
    void testMainControllerPointsByCustomerId() {
        HttpEntity<String> entity = new HttpEntity<>(null, new HttpHeaders());
        ResponseEntity<String> response = new RestTemplate().exchange("http://localhost:8080/customers/1/points",
                HttpMethod.GET, entity, String.class);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);

        String responseBody = response.getBody();
        String status = JsonPath.parse(responseBody).read("$.status");
        Assertions.assertEquals(status, "OK");

        List<Map<String, Object>> customerPointsList = JsonPath.parse(responseBody).read("$.customerPointsList");
        Assertions.assertEquals(customerPointsList.size(), 1);

        //customer 1
        Map<String, Object> customer1 = customerPointsList.get(0);
        int totalPointsForCustomer1 = (int) customer1.get("totalPoints");
        Assertions.assertEquals(totalPointsForCustomer1, 750);

        Map<String, Integer> monthlyPointsForCustomer1 = (Map<String, Integer>) customer1.get("monthlyPointsMap");
        Assertions.assertEquals(monthlyPointsForCustomer1.get("FEBRUARY"), 250);
        Assertions.assertEquals(monthlyPointsForCustomer1.get("JANUARY"), 500);
        Assertions.assertEquals(monthlyPointsForCustomer1.get("DECEMBER"), 0);
    }

    @Test
    void testMainControllerPoints() {
        HttpEntity<String> entity = new HttpEntity<>(null, new HttpHeaders());
        ResponseEntity<String> response = new RestTemplate().exchange("http://localhost:8080/customers/points",
                HttpMethod.GET, entity, String.class);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);

        String responseBody = response.getBody();
        String status = JsonPath.parse(responseBody).read("$.status");
        Assertions.assertEquals(status, "OK");

        List<Map<String, Object>> customerPointsList = JsonPath.parse(responseBody).read("$.customerPointsList");
        Assertions.assertEquals(customerPointsList.size(), 3);

        //customer 1
        Map<String, Object> customer1 = customerPointsList.get(0);
        int totalPointsForCustomer1 = (int) customer1.get("totalPoints");
        Assertions.assertEquals(totalPointsForCustomer1, 750);

        Map<String, Integer> monthlyPointsForCustomer1 = (Map<String, Integer>) customer1.get("monthlyPointsMap");
        Assertions.assertEquals(monthlyPointsForCustomer1.get("FEBRUARY"), 250);
        Assertions.assertEquals(monthlyPointsForCustomer1.get("JANUARY"), 500);
        Assertions.assertEquals(monthlyPointsForCustomer1.get("DECEMBER"), 0);

        //customer 2
        Map<String, Object> customer2 = customerPointsList.get(1);
        int totalPointsForCustomer2 = (int) customer2.get("totalPoints");
        Assertions.assertEquals(totalPointsForCustomer2, 180);

        Map<String, Integer> monthlyPointsForCustomer2 = (Map<String, Integer>) customer2.get("monthlyPointsMap");
        Assertions.assertEquals(monthlyPointsForCustomer2.get("FEBRUARY"), 150);
        Assertions.assertEquals(monthlyPointsForCustomer2.get("JANUARY"), 30);
        Assertions.assertEquals(monthlyPointsForCustomer2.get("DECEMBER"), 0);

        //customer 3
        Map<String, Object> customer3 = customerPointsList.get(2);
        int totalPointsForCustomer3 = (int) customer3.get("totalPoints");
        Assertions.assertEquals(totalPointsForCustomer3, 370);

        Map<String, Integer> monthlyPointsForCustomer3 = (Map<String, Integer>) customer3.get("monthlyPointsMap");
        Assertions.assertEquals(monthlyPointsForCustomer3.get("FEBRUARY"), 350);
        Assertions.assertEquals(monthlyPointsForCustomer3.get("JANUARY"), 20);
        Assertions.assertEquals(monthlyPointsForCustomer3.get("DECEMBER"), 0);
    }





}
