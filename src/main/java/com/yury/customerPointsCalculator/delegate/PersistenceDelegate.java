package com.yury.customerPointsCalculator.delegate;

import com.yury.customerPointsCalculator.repository.TransactionRepository;
import com.yury.customerPointsCalculator.repository.CustomerRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Data
public class PersistenceDelegate {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private TransactionRepository transactionRepository;
}
