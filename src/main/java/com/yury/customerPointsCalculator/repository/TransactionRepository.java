package com.yury.customerPointsCalculator.repository;

import com.yury.customerPointsCalculator.entity.Transaction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {

    @Query("SELECT s FROM Transaction s WHERE date >= ?1")
    List<Transaction> findAllFromDate(Date date);

    @Query("SELECT s FROM Transaction s WHERE date >= ?1 AND customerId = ?2")
    List<Transaction> findAllFromDateAndCustomerId(Date date, Long customerId);

}
