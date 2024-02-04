package com.example.MortgageApplication;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.io.*;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MortgageServiceTests {

    @Test
    void testReadCustomerInfoFromReader() throws IOException {
        String fileName = "src/main/resources/files/prospects.txt";

        // Creating the MortgageService instance
        MortgageService mortgageService = new MortgageService();

        // Calling the method to test
        List<Customer> customers = mortgageService.readCustomerInfoFromFile(fileName);

        Customer firstCustomer = customers.get(0);
        Customer secondCustomer = customers.get(1);
        Customer thirdCustomer = customers.get(2);
        Customer fourthCustomer = customers.get(3);

        // Assertions
        assertEquals(4, customers.size());

        assertEquals("Juha", firstCustomer.getCustomerName());
        assertEquals(1000.0, firstCustomer.getTotalLoan());
        assertEquals(5, firstCustomer.getInterest());
        assertEquals(2, firstCustomer.getYears());

        assertEquals("Karvinen", secondCustomer.getCustomerName());
        assertEquals(4356, secondCustomer.getTotalLoan());
        assertEquals(1.27, secondCustomer.getInterest());
        assertEquals(6, secondCustomer.getYears());

        assertEquals("Claes Månsson", thirdCustomer.getCustomerName());
        assertEquals(1300.55, thirdCustomer.getTotalLoan());
        assertEquals(8.67, thirdCustomer.getInterest());
        assertEquals(2, thirdCustomer.getYears());

        assertEquals("Clarencé Andersson", fourthCustomer.getCustomerName());
        assertEquals(2000.0, fourthCustomer.getTotalLoan());
        assertEquals(6, fourthCustomer.getInterest());
        assertEquals(4, fourthCustomer.getYears());
    }

    @Test
    void testCalculateMonthlyPayment() {
        // Creating a sample Customer instance
        Customer customer = new Customer("Sample", 1000, 5, 10);

        MortgageService mortgageService = new MortgageService();

        // Calling the method to test
        double monthlyPayment = mortgageService.calculateMonthlyPayment(customer);

        // Assertion
        assertEquals(10.61, monthlyPayment, 0.01);
    }
}
