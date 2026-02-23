Feature: Car Loan Selecting
    Scenario:Car Loan Emi Calculator
        Given the user is on the hdfc bank Website
        When the user click on the Discover Products
        And the clicks on the Calculator
        And from dropdown menu user can click on Car Loan Emi - New Calculator
        Then the user should be redirected to the Car Loan EMI page
        And the user should see a Car Loan Emi - New Message