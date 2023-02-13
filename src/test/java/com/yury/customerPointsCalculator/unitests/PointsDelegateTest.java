package com.yury.customerPointsCalculator.unitests;

import com.yury.customerPointsCalculator.Response.GetPointsResponse;
import com.yury.customerPointsCalculator.delegate.PersistenceDelegate;
import com.yury.customerPointsCalculator.delegate.PointsDelegate;
import com.yury.customerPointsCalculator.entity.Customer;
import com.yury.customerPointsCalculator.entity.Transaction;
import com.yury.customerPointsCalculator.repository.CustomerRepository;
import com.yury.customerPointsCalculator.repository.TransactionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PointsDelegateTest {

    @Mock
    PersistenceDelegate persistenceDelegate;

    @Mock
    CustomerRepository customerRepository;

    @Mock
    TransactionRepository transactionRepository;

    @Spy
    PointsDelegate pointsDelegate;

    @BeforeEach
    public void init() {
        pointsDelegate.setPersistenceDelegate(persistenceDelegate);

        when(persistenceDelegate.getCustomerRepository()).thenReturn(customerRepository);
        when(persistenceDelegate.getTransactionRepository()).thenReturn(transactionRepository);
    }

    @Test
    public void getPointsResponse_NormalFlow_ShouldReturnSomePoints() {

        when(customerRepository.findAll()).thenReturn(getCustomers());

        when(transactionRepository.findAllFromDate(any())).thenReturn(getTransactions());

        GetPointsResponse response = (GetPointsResponse) pointsDelegate.getPointsResponse(null);

        Assertions.assertEquals(response.getStatus(), HttpStatus.OK);
        Assertions.assertEquals(response.getCustomerPointsList().get(0).getTotalPoints(), 90);
    }

    @Test
    public void getPointsResponse_NormalFlow_ShouldReturnSomePointsFor1Customer() {

        when(customerRepository.findById(any())).thenReturn(Optional.of(getCustomers().get(0)));

        when(transactionRepository.findAllFromDateAndCustomerId(any(), any())).thenReturn(getTransactionsForCustomer1());

        GetPointsResponse response = (GetPointsResponse) pointsDelegate.getPointsResponse(1l);

        Assertions.assertEquals(response.getStatus(), HttpStatus.OK);
        Assertions.assertEquals(response.getCustomerPointsList().get(0).getTotalPoints(), 110);
    }

    @Test
    public void getPointsResponse_NormalFlow_ShouldReturnZeroPoints() {
        when(customerRepository.findAll()).thenReturn(getCustomers());

        when(transactionRepository.findAllFromDate(any())).thenReturn(getTransactionsWithZeroPoints());

        GetPointsResponse response = (GetPointsResponse) pointsDelegate.getPointsResponse(null);

        Assertions.assertEquals(response.getStatus(), HttpStatus.OK);
        Assertions.assertEquals(response.getCustomerPointsList().get(0).getTotalPoints(), 0);

    }

    @Test
    public void getPointsResponse_TestZeroTransactions() {
        when(customerRepository.findAll()).thenReturn(getCustomers());

        when(transactionRepository.findAllFromDate(any())).thenReturn(new ArrayList<>());

        GetPointsResponse response = (GetPointsResponse) pointsDelegate.getPointsResponse(null);

        Assertions.assertEquals(response.getStatus(), HttpStatus.OK);
    }

    @Test
    public void getPointsResponse_TestZeroCustomersAndTransactions() {
        when(customerRepository.findAll()).thenReturn(new ArrayList<>());

        when(transactionRepository.findAllFromDate(any())).thenReturn(new ArrayList<>());

        GetPointsResponse response = (GetPointsResponse) pointsDelegate.getPointsResponse(null);

        Assertions.assertEquals(response.getStatus(), HttpStatus.OK);
    }

    private List<Customer> getCustomers() {
        Customer customer = new Customer();
        customer.setId(1l);
        customer.setFirstName("Mike");
        customer.setLastName("Test");

        List<Customer> customers = new ArrayList<>();
        customers.add(customer);

        return customers;
    }

    private List<Transaction> getTransactionsForCustomer1() {
        Transaction transaction1 = new Transaction();
        transaction1.setId(1l);
        transaction1.setAmount(130l);
        transaction1.setDate(new Date());
        transaction1.setCustomerId(1l);

        List<Transaction> transactions = new ArrayList<>();

        transactions.add(transaction1);

        return transactions;
    }

    private List<Transaction> getTransactions() {
        Transaction transaction1 = new Transaction();
        transaction1.setId(1l);
        transaction1.setAmount(40l);
        transaction1.setDate(new Date());
        transaction1.setCustomerId(1l);

        Transaction transaction2 = new Transaction();
        transaction2.setId(2l);
        transaction2.setAmount(120l);
        transaction2.setDate(new Date());
        transaction2.setCustomerId(1l);

        List<Transaction> transactions = new ArrayList<>();

        transactions.add(transaction1);
        transactions.add(transaction2);

        return transactions;
    }

    private List<Transaction> getTransactionsWithZeroPoints() {
        Transaction transaction1 = new Transaction();
        transaction1.setId(1l);
        transaction1.setAmount(40l);
        transaction1.setDate(new Date());
        transaction1.setCustomerId(1l);

        List<Transaction> transactions = new ArrayList<>();

        transactions.add(transaction1);

        return transactions;
    }


}
