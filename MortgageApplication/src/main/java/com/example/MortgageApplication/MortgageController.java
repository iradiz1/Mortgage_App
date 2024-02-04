package com.example.MortgageApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MortgageController {

    @Autowired
    private MortgageService mortgageService;
    private static final String UPLOAD_DIR = "src/main/resources/files/";
    private List<Customer> defaultCustomers;

    /**
     * Handles GET requests to "/mortgage" endpoint
     * Reads default customer data from a file, calculates monthly payments, and updates the model with mortgage information
     * @param model Spring Model object for storing and passing data to the view
     * @return The view name ("mortgage")
     */
    @GetMapping("/mortgage")
    public String getMortgageInfo(Model model) {
        try {
            // Read data from the default file if not already read
            if (defaultCustomers == null) {
                String prospects = "src/main/resources/files/prospects.txt";
                defaultCustomers = mortgageService.readCustomerInfoFromFile(prospects);
            }

            // Calculate monthly payments and update the model
            calculateAndAddToModel(defaultCustomers, model);
        } catch (IOException e) {
            model.addAttribute("mortgageInfoList", "Error reading mortgage information");
        }

        return "mortgage";
    }

    /**
     * Handles POST requests to "/upload" endpoint
     * Saves the uploaded file, reads customer information, calculates monthly payments, and updates the model
     * @param file Represents the uploaded file
     * @param model Spring Model object for storing and passing data to the view
     * @return The view name ("mortgage")
     */
    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, Model model) {
        try {
            // Get the file bytes and save it to the specified directory
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOAD_DIR + file.getOriginalFilename());
            Files.write(path, bytes);

            List<Customer> customers = mortgageService.readCustomerInfoFromFile(String.valueOf(path));

            calculateAndAddToModel(customers, model);
        } catch (IOException e) {
            model.addAttribute("message", "Error processing mortgage information");
        }

        return "mortgage";
    }

    /**
     * Helper method to calculate monthly payments for a list of customers and adds the mortgage information to the model
     * @param customers List of Customer objects
     * @param model Spring Model object for storing and passing data to the view
     */
    private void calculateAndAddToModel(List<Customer> customers, Model model) {
        List<String> mortgageInfoList = new ArrayList<>();
        int prospectNumber = 1;

        // Loop through each customer to calculate monthly payments
        for (Customer customer : customers) {
            double monthlyPayment = mortgageService.calculateMonthlyPayment(customer);
            String message = String.format("Prospect %d: %s wants to borrow %.2f € for a period of %d years and pay %.2f € each month",
                    prospectNumber++, customer.getCustomerName(), customer.getTotalLoan(), customer.getYears(), monthlyPayment);
            mortgageInfoList.add(message);
        }
        
        // Add the mortgage information list to the model for rendering in the view
        model.addAttribute("mortgageInfoList", mortgageInfoList);
    }
}
