package com.example.MortgageApplication;
/**
 *  Customer class that represents a customer with mortgage details.
 */
public class Customer {
    public String customerName;
    public double totalLoan;
    public double interest;
    public int years;

    public Customer(String customerName, double totalLoan, double interest, int years)
    {
        this.customerName = customerName;
        this.totalLoan = totalLoan;
        this.interest = interest;
        this.years = years;
    }

    public String getCustomerName()
    {
        return customerName;
    }

    public double getTotalLoan()
    {
        return totalLoan;
    }

    public double getInterest()
    {
        return interest;
    }

    public int getYears()
    {
        return years;
    }
}