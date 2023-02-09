package com.yury.customerPointsCalculator.repository;

import com.yury.customerPointsCalculator.entity.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

}

