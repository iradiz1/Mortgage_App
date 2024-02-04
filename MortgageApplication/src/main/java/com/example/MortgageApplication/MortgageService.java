package com.example.MortgageApplication;

import com.example.MortgageApplication.Customer;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Service
public class MortgageService {

    /**
     * Reads customer information from a file and returns a list of Customer objects.
     * It handles parsing the file, skipping header lines, empty lines, and processing customer details.
     * @param fileName The name of the file containing customer information
     * @return A list of Customer objects with details read from the file.
     * @throws IOException If an IO error occurs while reading the file.
     */
    public List<Customer> readCustomerInfoFromFile(String fileName) throws IOException {
        List<Customer> customers = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        Boolean headerLine = true;
        String line;
        while ((line = br.readLine()) != null) {
            // Skip the header line
            if (headerLine) {
                headerLine = false;
                continue;
            }

            // Skip the empty lines
            if (line.trim().isEmpty()) {
                continue;
            }

            // Check for the dot at the end of the file
            if (line.equals(".")) {
                break;
            }

            // Replace the comma and the quotation marks in the customer name
            if (line.startsWith("\"")) {
                line = line.replaceFirst(",", " ");
                line = line.replaceAll("\"", "");
            }

            String[] customerInfo = line.split(",");
            String customerName = customerInfo[0];
            double totalLoan = Double.parseDouble(customerInfo[1].trim());
            double interestRate = Double.parseDouble(customerInfo[2].trim());
            int years = Integer.parseInt(customerInfo[3].trim());

            // Add the customer to the arraylist
            customers.add(new Customer(customerName, totalLoan, interestRate, years));
        }

        br.close();
        return customers;
    }

    /**
     * Calculates the monthly mortgage payment for a given customer based on their loan details
     * @param customer A Customer object containing loan details
     * @return The calculated monthly mortgage payment
     */
    public double calculateMonthlyPayment(Customer customer) {
        double monthlyInterestRate = customer.getInterest() / 100 / 12;
        int totalPayments = customer.getYears() * 12;
        double numerator = customer.getTotalLoan() * monthlyInterestRate;
        double denominator = 1;
        for (int i = 0; i < totalPayments; i++)
        {
            denominator *= (1 + monthlyInterestRate);
        }
        return numerator / (1 - (1 / denominator));
    }

}
